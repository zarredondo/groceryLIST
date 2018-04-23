package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 3/16/2018.
 */

public class Recipe {
    private int image;
    private String name;
    private String percentage;
    private String instructions;
    private List<String> ingredients = new ArrayList<>();

    public Recipe() {

    }

    public Recipe(String name, String percentage, String instructions, List<String> ingredients) {
        this.name = name;
        this.percentage = percentage;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}
