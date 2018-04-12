package edu.utexas.ece.pugs.grocerylist;

import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ListNonFoodItem extends ShoppingListItem {
    protected String name;
    protected Quantity quantity;

    public ListNonFoodItem(String name, Quantity quantity, Date addedDate, Date purchaseDate) {
        super(addedDate, purchaseDate);
        this.name = name;
        this.quantity = quantity;
    }
}
