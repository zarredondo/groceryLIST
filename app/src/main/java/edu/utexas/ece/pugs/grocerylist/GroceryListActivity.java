package edu.utexas.ece.pugs.grocerylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mashape.p.spoonacularrecipefoodnutritionv1.controllers.APIController;

import org.json.JSONException;

import java.util.Map;

import edu.utexas.ece.pugs.grocerylist.SpoonacularControllers.DynamicCallBack;
import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Ingredient;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;

public class GroceryListActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView groceryList;
    private ShoppingList shoppingList = ShoppingList.getInstance();
    private ArrayAdapter<FoodItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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

                controller.getAutocompleteIngredientSearchAsync(name, findIngredientCallback);
                Map<String, Object> itemEntry = findIngredientCallback.getResult();

                FoodItem food = new FoodItem(itemEntry);
                shoppingList.addItem(food);
            }
        });
 /*
        if (shoppingList.isEmpty()){
            //TODO Set background to something spicy
        }
        else {
            //TODO Display all list items in shoppingList
            //adapter = new ArrayAdapter<Food>(this,
            //        android.R.layout.simple_list_item_1,
            //        shoppingList.getGroceryList()
            //);
        }
        */
    }


    protected void onPurchased() {
        //TODO
    }
}
