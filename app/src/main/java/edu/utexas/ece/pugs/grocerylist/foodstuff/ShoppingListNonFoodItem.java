package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingListNonFoodItem {
    protected String name;
    protected Quantity quantity;
    protected Date purchaseDate;
    protected Date addedDate;


    public ShoppingListNonFoodItem(String name, Quantity quantity, Date addedDate, Date purchaseDate) {

        this.name = name;
        this.quantity = quantity;
        this.addedDate = addedDate;
        this.purchaseDate = purchaseDate;
    }
}
