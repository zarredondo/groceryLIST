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

public class FindRecipeByNutrientsActivity extends AppCompatActivity {

    EditText maxCalories;
    EditText maxCarbs;
    EditText maxFat;
    EditText maxProtein;
    EditText minCalories;
    EditText minCarbs;
    EditText minFat;
    EditText minProtein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_recipe_by_nutrients);

        maxCalories = (EditText) findViewById(R.id.max_calories);
        maxCarbs = (EditText) findViewById(R.id.max_carbs);
        maxFat = (EditText) findViewById(R.id.max_fat);
        maxProtein = (EditText) findViewById(R.id.max_protein);
        minCalories = (EditText) findViewById(R.id.min_calories);
        minCarbs = (EditText) findViewById(R.id.min_carbs);
        minFat = (EditText) findViewById(R.id.min_fat);
        minProtein = (EditText) findViewById(R.id.min_protein);

        Button nutrientRecipeButton = (Button) findViewById(R.id.nutrientRecipeButton);
        nutrientRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("maxCalories", maxCalories.getText().toString());
                returnIntent.putExtra("maxCarbs", maxCarbs.getText().toString());
                returnIntent.putExtra("maxFat", maxFat.getText().toString());
                returnIntent.putExtra("maxProtein", maxProtein.getText().toString());
                returnIntent.putExtra("minCalories", minCalories.getText().toString());
                returnIntent.putExtra("minCarbs", minCarbs.getText().toString());
                returnIntent.putExtra("minFat", minFat.getText().toString());
                returnIntent.putExtra("minProtein", minProtein.getText().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}