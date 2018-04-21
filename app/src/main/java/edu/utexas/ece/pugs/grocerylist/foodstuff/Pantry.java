package edu.utexas.ece.pugs.grocerylist.foodstuff;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class Pantry {
    private static Pantry instance = new Pantry();

    private Map<String, PantryItem> pantryItems;

    private Pantry() {
        pantryItems = new HashMap<String, PantryItem>();
    }

    public static Pantry getInstance() {
        return instance;
    }

    public Map<String, PantryItem> getPantryItems() {
        return pantryItems;
    }

    public void setPantryItems(Map<String, PantryItem> pantryItems) {
        this.pantryItems = pantryItems;
    }

    /**
     * Adds purchase to our pantry. If there is no PantryItem corresponding
     * to the purchase's id, then a new PantryItem is created and put into the
     * pantryItems map.
     *
     * @param purchase
     */
    public void addPurchase(Purchase purchase) {
        if(pantryItems.containsKey(purchase.getId())) {
            pantryItems.get(purchase.getId()).addPurchase(purchase);
        } else {
            PantryItem pantryItem = new PantryItem();
            pantryItem.addPurchase(purchase);
            pantryItems.put(purchase.getId(), pantryItem);
        }
    }
}
