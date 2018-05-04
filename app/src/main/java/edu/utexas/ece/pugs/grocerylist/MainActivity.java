package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration;
import com.mashape.p.spoonacularrecipefoodnutritionv1.SpoonacularAPIClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListNonFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Toolbar mToolbar;
    private TextView mTextView;
    private Pantry pantry;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("groceryLIST");

        mTextView = (TextView) findViewById(R.id.main_page_textview);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        User.getInstance().setFirebaseEnable(true);

        if (currentUser == null) {

            sendToStart();

        } else {
            User.getInstance().setTriplet(currentUser.getUid(), currentUser.getEmail(), currentUser.getDisplayName());

            User.getInstance().getPantryReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Pantry.getInstance().setPantryItems((Map<String, PantryItem>) dataSnapshot.getValue());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            User.getInstance().getFoodItemListReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {Map<String, ShoppingListFoodItem> shoppingListFoodItems = (Map<String, ShoppingListFoodItem>) dataSnapshot.getValue();
                    if (shoppingListFoodItems != null) {
                        ShoppingList.getInstance().setFoodItems(shoppingListFoodItems);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            User.getInstance().getNonFoodItemListReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<ShoppingListNonFoodItem> shoppingListNonFoodItems = (List<ShoppingListNonFoodItem>) dataSnapshot.getValue();
                    if (shoppingListNonFoodItems != null) {
                        ShoppingList.getInstance().setNonFoodItems(shoppingListNonFoodItems);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            Intent startIntent = new Intent(MainActivity.this, GroceryListActivity.class);
            startActivity(startIntent);
            finish();
        }

    }

    private void sendToStart() {

        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }
}
