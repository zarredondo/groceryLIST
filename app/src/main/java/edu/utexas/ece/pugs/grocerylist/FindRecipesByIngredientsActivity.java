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

public class FindRecipesByIngredientActivity extends AppCompatActivity {
    EditText ingredients;
    EditText ingredientLimitLicense;
    EditText ingredientNumber;
    EditText ingredientRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingredients = (EditText) findViewById(R.id.ingredients);
        ingredientLimitLicense = (EditText) findViewById(R.id.ingredient_limit_license);
        ingredientNumber = (EditText) findViewById(R.id.ingredient_number);
        ingredientRanking = (EditText) findViewById(R.id.ingredient_ranking);

        Button ingredientRecipeButton = (Button) findViewById(R.id.ingredientRecipeButton);
        ingredientRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("ingredients", ingredients.getText().toString());
                returnIntent.putExtra("pantryLimitLicense", ingredientLimitLicense.getText().toString());
                returnIntent.putExtra("ingredientNumber", ingredientNumber.getText().toString());
                returnIntent.putExtra("ingredientRanking", ingredientRanking.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
