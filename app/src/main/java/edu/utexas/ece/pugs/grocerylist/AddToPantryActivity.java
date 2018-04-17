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

import java.util.Calendar;

public class AddToPantryActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    EditText nameEditText;
    EditText idEditText;
    EditText amountEditText;
    EditText unitEditText;
    Button fireBaseButton;

    Food testFood1;
    Quantity testQuantity;
    Ingredient testIngredient;
    PantryItem testPantryItem;


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

        fireBaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Food food = new Food(Integer.parseInt(idEditText.getText().toString()),
                        nameEditText.getText().toString());
                Quantity quantity = new Quantity(amountEditText.getText().toString(),
                        unitEditText.getText().toString(), unitEditText.getText().toString(),
                        unitEditText.getText().toString());
                Ingredient ingredient = new Ingredient(food, quantity);*/
                testFood1 = new Food(6901, "lime");
                testQuantity = new Quantity("5", "pieces", "pc", "pieces");
                testIngredient = new Ingredient(testFood1, testQuantity);

                mDatabase.child("pantry items").child(mUser.getUid()).setValue(
                        new PantryItem(testFood1, Calendar.getInstance().getTime(),
                                Calendar.getInstance().getTime()));

                //mDatabase.child("ingredient").setValue(testIngredient);
                /*mDatabase.child("ingredient").child("food").setValue(testFood1);*/
                /*foods = new ArrayList<Food>(Arrays.asList(testFood1, testFood2));*/
                /*mDatabase.child("mynewfoods").setValue(foods);*/
                /*mDatabase.child("mynewfoods").p;*/
                /*mDatabase.child("pantries").child("")*/
            }
        });
    }
}
