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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private EditText mDisplayName, mEmail, mPassword;
    private Button mCreateBtn;

    private android.support.v7.widget.Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Registration Fields
        mDisplayName = (EditText) findViewById(R.id.reg_display_name);
        mEmail = (EditText) findViewById(R.id.reg_email);
        mPassword = (EditText) findViewById(R.id.reg_password);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("groceryLIST");

        mCreateBtn = (Button) findViewById(R.id.reg_create_btn);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String displayName = mDisplayName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                registerUser(displayName, email, password);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.main_logout_btn) {
            mAuth.signOut();
            sendToStart();
        }
        return true;
    }

    private void registerUser(final String displayName, final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("success", "createUserWithEmail:success");

                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            User user = new User(email, displayName);

                            mDatabase.child("users").child(firebaseUser.getUid()).setValue(user);


                            Toast.makeText(RegisterActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();

                            Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                            finish();

                        } else {
                            //Log.w("failure", "createUserWithEmail:failure", task.getException());

                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void sendToStart() {

        Intent startIntent = new Intent(RegisterActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();

    }
}