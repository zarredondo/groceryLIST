package edu.utexas.ece.pugs.grocerylist;

import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingListItem {
    protected Date addedDate;
    protected Date purchaseDate;

    public ShoppingListItem(Date addedDate, Date purchaseDate) {
        this.addedDate = addedDate;
        this.purchaseDate = purchaseDate;
    }
}
