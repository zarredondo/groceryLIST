package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class SettingsActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button signOutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mAuth = FirebaseAuth.getInstance();
        signOutButton = (Button) findViewById(R.id.settings_sign_out_btn);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    void signOut() {
        mAuth.signOut();
        User.getInstance().signOut();
        Intent startIntent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(startIntent);
        finish();

    }

}
