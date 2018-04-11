package edu.utexas.ece.pugs.grocerylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class PantryItem {
    protected Food food;
    protected List<Purchase> purchases;

    public PantryItem(Food food, Purchase purchase) {
        this.food = food;
        purchases = new ArrayList<>();
        purchases.add(purchase);
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
    }
}
