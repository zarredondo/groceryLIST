package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingListNonFoodItem {


    protected String name;
    protected Quantity quantity;

    public ShoppingListNonFoodItem() {

    }

    public ShoppingListNonFoodItem(String name, Quantity quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

}
