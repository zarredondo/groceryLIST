package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class PantryItem {
    List<Purchase> purchases;

    public PantryItem() {
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }
}
