package edu.utexas.ece.pugs.grocerylist;

/**
 * Created by manuellopez on 4/29/18.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BaseActivity extends AppCompatActivity {


    RadioGroup radioGroup1;
    RadioButton deals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        deals = (RadioButton)findViewById(R.id.pantry);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                Intent in;
                Log.i("matching", "matching inside1 bro" + checkedId);
                switch (checkedId)
                {
                    case R.id.pantry:
                        Log.i("matching", "matching inside1 matching" +  checkedId);
                        in=new Intent(getBaseContext(),PantryActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.grocerylist:
                        Log.i("matching", "matching inside1 watchlistAdapter" + checkedId);
                        in = new Intent(getBaseContext(), GroceryListActivity.class);
                        startActivity(in);
                        break;
                    case R.id.recipe:
                        Log.i("matching", "matching inside1 rate" + checkedId);
                        in = new Intent(getBaseContext(), RecipeActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.settings:
                        in = new Intent(getBaseContext(), SettingsActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        default:
                        break;
                }
            }
        });
    }
}