package edu.utexas.ece.pugs.grocerylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mashape.p.spoonacularrecipefoodnutritionv1.controllers.APIController;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;

import edu.utexas.ece.pugs.grocerylist.SpoonacularControllers.DynamicCallBack;
import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Ingredient;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class GroceryListActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private TextView mTextMessage;
    private LinearLayout groceryList;
    private ShoppingList shoppingList = ShoppingList.getInstance();
    private ArrayAdapter<FoodItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);

        //Instantiation of APIController and APICallback response system
        final APIController controller = APIController.getInstance();
        final DynamicCallBack findIngredientCallback = new DynamicCallBack();
        groceryList = findViewById(R.id.groceryList);

        final Button addItemButton = findViewById(R.id.addItemButton);

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

        DatabaseReference shoppingListReference = User.getInstance().getShoppingListReference();
        shoppingListReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        populateList();
    }


    protected void onPurchased() {
        //TODO
    }

    protected void populateList(){
        DatabaseReference shoppingListReference = User.getInstance().getFoodItemListReference();
        shoppingListReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //TODO populate the list
                ArrayList<ShoppingListFoodItem> foodList = (ArrayList<ShoppingListFoodItem>) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
