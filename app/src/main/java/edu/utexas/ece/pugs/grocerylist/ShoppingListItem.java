package edu.utexas.ece.pugs.grocerylist;

import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingListItem {
    protected String name;
    protected Quantity quantity;
    protected Date addedDate;
    protected Date purchaseDate;

    public ShoppingListItem(String name, Quantity quantity, Date addedDate) {
        this.name = name;
        this.quantity = quantity;
        this.addedDate = addedDate;
    }
}
