package edu.utexas.ece.pugs.grocerylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class RecipeList {
    private static RecipeList uniqueInstance = new RecipeList();
    private List<Recipe> RecipeList;

    private RecipeList() {
        this.RecipeList = new ArrayList<>();
    }

    public static RecipeList getInstance() {
        return uniqueInstance;
    }

    public void setRecipeList(List<Recipe> recipes) {
        this.RecipeList = recipes;
    }

    public List<Recipe> getRecipeList() {
        return RecipeList;
    }
}
