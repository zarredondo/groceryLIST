package edu.utexas.ece.pugs.grocerylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import edu.utexas.ece.pugs.grocerylist.foodstuff.Recipe;
import edu.utexas.ece.pugs.grocerylist.foodstuff.RecipeList;

/**
 * Created by Brandon on 3/17/2018.
 */

public class RecipeDetailsActivity extends AppCompatActivity {
    List<Recipe> recipeList = RecipeList.getInstance().getRecipeList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

    }
}
