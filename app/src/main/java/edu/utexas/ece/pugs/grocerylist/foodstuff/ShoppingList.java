package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    private static ShoppingList uniqueInstance = new ShoppingList();

    private List<ListFoodItem> listFoodItems;
    private List<ShoppingListNonFoodItem> nonFoodItems;

    private ShoppingList() {
        listFoodItems = new ArrayList<>();
        nonFoodItems = new ArrayList<>();
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

    public List<ShoppingListNonFoodItem> getNonFoodItems() {
        return nonFoodItems;
    }

    public void setNonFoodItems(List<ShoppingListNonFoodItem> nonFoodItems) {
        this.nonFoodItems = nonFoodItems;
    }
}
