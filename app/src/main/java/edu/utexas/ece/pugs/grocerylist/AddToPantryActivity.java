package edu.utexas.ece.pugs.grocerylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Ingredient;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ListNonFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Purchase;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;
import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class AddToPantryActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    EditText nameEditText;
    EditText idEditText;
    EditText amountEditText;
    EditText unitEditText;
    Button fireBaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_pantry);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        idEditText = (EditText) findViewById(R.id.id_edit_text);
        amountEditText = (EditText) findViewById(R.id.amount_edit_text);
        unitEditText = (EditText) findViewById(R.id.unit_edit_text);
        fireBaseButton = (Button) findViewById(R.id.add_to_firebase_button);

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("mew"); arrayList.add("mewtwo");

        fireBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFoodItem listFoodItem1 = new ListFoodItem("69", "original", "pineapple",
                        new Quantity("3", "oz", "oz", "ounces"), "hard",
                        arrayList, "produce", "pineapple.jpg", arrayList, new Date());
                ListFoodItem listFoodItem2 = new ListFoodItem("420", "original", "pineapple",
                        new Quantity("3", "oz", "oz", "ounces"), "hard",
                        arrayList, "produce", "pineapple.jpg", arrayList, new Date());

                Purchase newPurchase1 = new Purchase(listFoodItem1);
                Purchase newPurchase2 = new Purchase(listFoodItem2);

                ListNonFoodItem listNonFoodItem1 = new ListNonFoodItem("batteries",
                        new Quantity("3", "batteries", "batteries", "batteries"));
                ListNonFoodItem listNonFoodItem2 = new ListNonFoodItem("hat",
                        new Quantity("1", "articles", "articles", "articles"));

                Pantry.getInstance().addPurchase(newPurchase1);
                Pantry.getInstance().addPurchase(newPurchase2);
                ShoppingList.getInstance().getListFoodItems().add(listFoodItem1);
                ShoppingList.getInstance().getListFoodItems().add(listFoodItem2);
                ShoppingList.getInstance().getNonFoodItems().add(listNonFoodItem1);
                ShoppingList.getInstance().getNonFoodItems().add(listNonFoodItem2);

                User.getInstance().getPantryReference().setValue(Pantry.getInstance().getPantryItems());
                User.getInstance().getFoodItemListReference().setValue(ShoppingList.getInstance().getListFoodItems());
                User.getInstance().getNonFoodItemListReference().setValue(ShoppingList.getInstance().getNonFoodItems());
            }
        });
    }
}
