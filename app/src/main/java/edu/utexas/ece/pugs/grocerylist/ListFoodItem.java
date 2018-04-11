package edu.utexas.ece.pugs.grocerylist;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ListFoodItem extends ShoppingListItem {
    protected Date expirationDate;


    public ListFoodItem(String name, Quantity quantity, Date addedDate, Date expirationDate) {
        super(name, quantity, addedDate);
        this.expirationDate = expirationDate;
    }

    public Purchase generatePurchase() {
        return new Purchase(quantity, Calendar.getInstance().getTime(), expirationDate);
    }
}
