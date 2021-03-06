package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class PantryItem {
    List<Purchase> purchases;

    public PantryItem() {
        purchases = new ArrayList<Purchase>();
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public String getItemName() {
        if (!purchases.isEmpty()) {
            return purchases.get(0).getName();
        } else {
            return "";
        }
    }


    public void setItemName(String name){
        if (!purchases.isEmpty()) {
            purchases.get(0).setName(name);
        }
    }

    public String getId() {
        if (!purchases.isEmpty()) {
            return purchases.get(0).getId();
        } else {
            return "";
        }
    }

}
