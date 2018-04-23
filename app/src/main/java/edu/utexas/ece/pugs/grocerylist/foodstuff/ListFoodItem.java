package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.Date;

import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ListFoodItem extends FoodItem {
    protected Date addedDate;
    protected Date purchaseDate;

    public ListFoodItem(Date addedDate, Date purchaseDate) {
        this.addedDate = addedDate;
        this.purchaseDate = purchaseDate;
    }

    public void onPurchased() {

    }
}
