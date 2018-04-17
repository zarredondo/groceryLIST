package edu.utexas.ece.pugs.grocerylist;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class Ingredient {
    protected Food food;
    protected Quantity quantity;

    public Ingredient(Food food, Quantity quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Ingredient() {
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
