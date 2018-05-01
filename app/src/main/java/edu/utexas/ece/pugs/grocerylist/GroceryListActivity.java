package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration;
import com.mashape.p.spoonacularrecipefoodnutritionv1.SpoonacularAPIClient;
import com.mashape.p.spoonacularrecipefoodnutritionv1.controllers.APIController;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.utexas.ece.pugs.grocerylist.SpoonacularControllers.DynamicCallBack;
import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Ingredient;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListNonFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class GroceryListActivity extends BaseActivity {

    //Variables used throughout the activity

    private TextView foodListHeader = new TextView(this);
    private TextView nonFoodListHeader = new TextView(this);
    private LinearLayout groceryList;
    private List<ShoppingListFoodItem> foodItemList;
    private List<ShoppingListNonFoodItem> nonFoodItemList;
    private Button addItemButton;
    private LinearLayout dynamicContent,bottonNavBar;

    private ShoppingList shoppingList = ShoppingList.getInstance();
    final String XMashapeKey = "TyI4LJpGVLmshLMmIsnLipUE0L8gp1zPJjKjsn2dx6UOeb2N84";
    private SpoonacularAPIClient client;
    private APIController controller;



    //Bottom Navigation Widget Listener Instantiation
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    item.setChecked(true);
                    Intent pantry = new Intent(getApplicationContext(), PantryActivity.class);// New activity
                    pantry.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(pantry);
                    break;
                case R.id.navigation_dashboard:
                    return false;
                case R.id.navigation_notifications:
                    item.setChecked(true);
                    Intent recipe = new Intent(getApplicationContext(), RecipeActivity.class);// New activity
                    recipe.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(recipe);
                    break;
            }
            return false;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration.initialize(this);
        client = new SpoonacularAPIClient();
        Configuration.setXMashapeKey(XMashapeKey);
        //setContentView(R.layout.activity_grocery_list);

        dynamicContent = (LinearLayout)  findViewById(R.id.dynamicContent);
        bottonNavBar= (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_grocery_list, null);
        dynamicContent.addView(wizard);


        //get the reference of RadioGroup.

        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton rb=(RadioButton)findViewById(R.id.grocerylist);

        // Change the corresponding icon and text color on nav button click.

        rb.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.ic_cart, 0,0);
        rb.setTextColor(Color.parseColor("#3F51B5"));

        //Instantiation of APIController and APICallback response system
        controller = client.getClient();

        final DynamicCallBack findIngredientCallback = new DynamicCallBack();

        groceryList = findViewById(R.id.groceryList);

        addItemButton = findViewById(R.id.addItemButton);

        ShoppingList shop = ShoppingList.getInstance();
        User user = User.getInstance();


        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final TextView item = findViewById(R.id.addItemText);
                String name = String.valueOf(item.getText());

                controller.createParseIngredientsAsync(name, 1, findIngredientCallback);
                Map<String, Object> itemEntry = findIngredientCallback.getResult();

                FoodItem food = new FoodItem(itemEntry);
                shoppingList.addItem(food);
            }
        });

        user.getFoodItemListReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foodItemList = new ArrayList<ShoppingListFoodItem>();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    ShoppingListFoodItem item = ds.getValue(ShoppingListFoodItem.class);
                    foodItemList.add(item);
                }

                showList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        user.getNonFoodItemListReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nonFoodItemList = new ArrayList<ShoppingListNonFoodItem>();
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    ShoppingListNonFoodItem item = ds.getValue(ShoppingListNonFoodItem.class);
                    nonFoodItemList.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    protected void onPurchased() {
        //TODO
    }

    protected void showList() {
        foodListHeader.setText("Food to Buy");
        groceryList.addView(foodListHeader);

        for (ShoppingListFoodItem i: foodItemList){
            CheckBox cb = new CheckBox(this);
            cb.setText(i.getName());
        }

        nonFoodListHeader.setText("Other Stuff to Buy");
        groceryList.addView(nonFoodListHeader);

        for (ShoppingListNonFoodItem i: nonFoodItemList){
            CheckBox cb = new CheckBox(this);
            cb.setText(i.getName());
        }
    }
}
