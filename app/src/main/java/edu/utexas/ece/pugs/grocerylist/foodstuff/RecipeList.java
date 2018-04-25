package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class RecipeList {
    private static RecipeList uniqueInstance = new RecipeList();
    private List<Recipe> recipes;

    public static RecipeList getUniqueInstance() {
        return uniqueInstance;
    }

    private RecipeList() {
        this.recipes = new ArrayList<>();
    }

    public static RecipeList getInstance() {
        return uniqueInstance;
    }

    public void setRecipeList(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipeList() {
        return recipes;
    }
}
