package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.Date;

import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingListFoodItem extends FoodItem {
    protected Date addedDate;
    protected Date purchaseDate;

    public ShoppingListFoodItem(Date addedDate, Date purchaseDate) {
        this.addedDate = addedDate;
        this.purchaseDate = purchaseDate;
    }

    public void onPurchased() {

    }
}
