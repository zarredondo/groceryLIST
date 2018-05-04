package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListNonFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;
import edu.utexas.ece.pugs.grocerylist.foodstuff.User;


public class SignInActivity extends AppCompatActivity {

    private EditText mEmail, mPassword;
    private Button mSignInButton;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private android.support.v7.widget.Toolbar mToolbar;
    private Map<String, PantryItem> pantryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("hi");

        // Registration Fields

        mEmail = (EditText) findViewById(R.id.sign_in_email);
        mPassword = (EditText) findViewById(R.id.sign_in_password);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.sign_in_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("groceryLIST");

        mSignInButton = (Button) findViewById(R.id.sign_in_btn);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                signInUser(email, password);
            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void signInUser(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent mainIntent = new Intent(SignInActivity.this, GroceryListActivity.class);

                            User.getInstance().setTriplet(user.getUid(), user.getEmail(), user.getDisplayName());
                            User.getInstance().addUserToDatabase();
                            new FirebaseLoader().execute(new String[]{});

                            startActivity(mainIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            /*Log.w("Authentication failed.", "signInWithEmail:failure", task.getException());*/
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void sendToStart() {

        Intent startIntent = new Intent(SignInActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();

    }
}

