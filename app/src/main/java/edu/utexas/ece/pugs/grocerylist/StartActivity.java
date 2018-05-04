package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration;
import com.mashape.p.spoonacularrecipefoodnutritionv1.SpoonacularAPIClient;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private Button mRegBtn;
    private Button mSignInBtn;
    private Drawable bckgrnd;
    String xMashapeKey = "groceryLIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration.initialize(getApplicationContext());
        SpoonacularAPIClient client = new SpoonacularAPIClient();
        Configuration.setXMashapeKey(xMashapeKey);

        mRegBtn = (Button) findViewById(R.id.start_reg_btn);
        mSignInBtn = (Button) findViewById(R.id.start_sign_in_btn);

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });

        mSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(StartActivity.this, SignInActivity.class);
                startActivity(signInIntent);
                finish();
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bckgrnd = getDrawable(R.drawable.login_background);
            getWindow().setBackgroundDrawable(bckgrnd);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


    }


}
