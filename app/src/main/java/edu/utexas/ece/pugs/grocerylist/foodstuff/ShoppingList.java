package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    private static ShoppingList uniqueInstance = new ShoppingList();

    private List<ShoppingListFoodItem> shoppingListFoodItems;
    private List<ShoppingListNonFoodItem> nonFoodItems;

    private ShoppingList() {
        listFoodItems = new ArrayList<>();
        nonFoodItems = new ArrayList<>();
    }

    public void addItem(FoodItem food){
        ShoppingListFoodItem e = (ShoppingListFoodItem)food;
        shoppingListFoodItems.add(e);
    }

    public static void addItem(String name){

    }

    public static ShoppingList getInstance() {
        return uniqueInstance;
    }

    public List<ShoppingListFoodItem> getShoppingListFoodItems() {
        return shoppingListFoodItems;
    }

    public void setShoppingListFoodItems(ArrayList<ShoppingListFoodItem> shoppingListFoodItems) {
        this.shoppingListFoodItems = shoppingListFoodItems;
    }

    public List<ShoppingListNonFoodItem> getNonFoodItems() {
        return nonFoodItems;
    }

    public void setNonFoodItems(ArrayList<ShoppingListNonFoodItem> nonFoodItems) {
        this.nonFoodItems = nonFoodItems;
    }
}
