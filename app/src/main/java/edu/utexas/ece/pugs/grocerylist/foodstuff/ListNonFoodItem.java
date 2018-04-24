package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ListNonFoodItem {
    protected String name;
    protected Quantity quantity;

    public ListNonFoodItem(String name, Quantity quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
