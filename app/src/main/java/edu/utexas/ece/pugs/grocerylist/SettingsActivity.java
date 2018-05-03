package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;

import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class SettingsActivity extends BaseActivity {
    FirebaseAuth mAuth;
    Button signOutButton;
    Button attributionButton;
    private LinearLayout dynamicContent;
    private LinearLayout bottonNavBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);

        dynamicContent = (LinearLayout) findViewById(R.id.dynamicContent);
        bottonNavBar = (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_settings, null);
        dynamicContent.addView(wizard);


        //get the reference of RadioGroup.

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.settings);

        // Change the corresponding icon and text color on nav button click.

        rb.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_settings, 0, 0);
        rb.setTextColor(Color.parseColor("#3F51B5"));

        mAuth = FirebaseAuth.getInstance();
        signOutButton = (Button) findViewById(R.id.settings_sign_out_btn);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        attributionButton = (Button) findViewById(R.id.attribution_btn);
        attributionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attributionIntent = new Intent(SettingsActivity.this, AttributionActivity.class);
                startActivity(attributionIntent);

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
