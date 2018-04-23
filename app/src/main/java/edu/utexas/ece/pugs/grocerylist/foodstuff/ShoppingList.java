package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.List;

import edu.utexas.ece.pugs.grocerylist.foodstuff.ListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ListNonFoodItem;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    private static ShoppingList uniqueInstance = new ShoppingList();

    private List<ListFoodItem> listFoodItems;
    private List<ListNonFoodItem> nonFoodItems;

    public ShoppingList() {

    }

    public static ShoppingList getInstance() {
        return uniqueInstance;
    }

    public List<ListFoodItem> getListFoodItems() {
        return listFoodItems;
    }

    public void setListFoodItems(List<ListFoodItem> listFoodItems) {
        this.listFoodItems = listFoodItems;
    }

    public List<ListNonFoodItem> getNonFoodItems() {
        return nonFoodItems;
    }

    public void setNonFoodItems(List<ListNonFoodItem> nonFoodItems) {
        this.nonFoodItems = nonFoodItems;
    }
}
