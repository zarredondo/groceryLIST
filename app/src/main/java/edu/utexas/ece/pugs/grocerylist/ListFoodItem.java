package edu.utexas.ece.pugs.grocerylist;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ListFoodItem extends ShoppingListItem {
    protected Food food;
    protected Date expirationDate;

    public ListFoodItem(Food food, Date addedDate, Date purchaseDate, Date expirationDate) {
        super(addedDate, purchaseDate);
        this.food = food;
        this.expirationDate = expirationDate;
    }

    public PantryItem generatePantryItem() {
        return new PantryItem(food, Calendar.getInstance().getTime(), expirationDate);
    }
}
