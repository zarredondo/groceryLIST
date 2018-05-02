package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    private static ShoppingList uniqueInstance = new ShoppingList();

    private static List<ShoppingListFoodItem> shoppingListFoodItems;
    private static List<ShoppingListNonFoodItem> nonFoodItems;

    private ShoppingList() {
        shoppingListFoodItems = new ArrayList<>();
        nonFoodItems = new ArrayList<>();
    }

    public void addItem(ShoppingListFoodItem food){
        shoppingListFoodItems.add(food);
        User.getInstance().getFoodItemListReference().setValue(shoppingListFoodItems);
    }


    public static void addItem(String name){

    }

    public static ShoppingList getInstance() {
        return uniqueInstance;
    }

    public List<ShoppingListFoodItem> getShoppingListFoodItems() {
        return shoppingListFoodItems;
    }

    public void setShoppingListFoodItems(List<ShoppingListFoodItem> shoppingListFoodItems) {
        this.shoppingListFoodItems = shoppingListFoodItems;
    }

    public List<ShoppingListNonFoodItem> getNonFoodItems() {
        return nonFoodItems;
    }

    public void setNonFoodItems(List<ShoppingListNonFoodItem> nonFoodItems) {
        this.nonFoodItems = nonFoodItems;
    }


}
