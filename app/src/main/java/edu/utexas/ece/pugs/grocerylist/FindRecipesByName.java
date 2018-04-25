package edu.utexas.ece.pugs.grocerylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Brandon on 4/23/2018.
 */

public class FindRecipesByName extends AppCompatActivity {

    EditText query;
    EditText cuisine;
    EditText diet;
    EditText excludeIngredients;
    EditText intolerances;
    EditText nameLimitLicense;
    EditText nameNumber;
    EditText offset;
    EditText type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipe_by_name);

        Button nameRecipeButton = (Button) findViewById(R.id.nameRecipeButton);
        nameRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("query", query.getText().toString());
                returnIntent.putExtra("cuisine", cuisine.getText().toString());
                returnIntent.putExtra("diet", diet.getText().toString());
                returnIntent.putExtra("excludeIngredients", excludeIngredients.getText().toString());
                returnIntent.putExtra("intolerances", intolerances.getText().toString());
                returnIntent.putExtra("nameLimitLicense", nameLimitLicense.getText().toString());
                returnIntent.putExtra("nameNumber", nameNumber.getText().toString());
                returnIntent.putExtra("offset", offset.getText().toString());
                returnIntent.putExtra("type", type.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
