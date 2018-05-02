package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    private static ShoppingList uniqueInstance = new ShoppingList();

    private List<ShoppingListNonFoodItem> nonFoodItems;
    private List<ShoppingListFoodItem> foodItems;

    private ShoppingList() {

        nonFoodItems = new ArrayList<>();
        foodItems = new ArrayList<>();
    }

    public void addItem(ShoppingListFoodItem food){
        if(!foodItems.contains(food)){
            foodItems.add(food);
        }
        if (User.getInstance().getFirebaseEnable()) {
            User.getInstance().getFoodItemListReference().setValue(foodItems);
        }
    }

    public int removeItem(String key){
        int x = -1;
        for(ShoppingListFoodItem m : foodItems){
            if(m.getId().equals(key)) {
                x = foodItems.indexOf(m);
                foodItems.remove(m);
            }
        }
        return x;
    }


    public static void addItem(String name){

    }

    public static ShoppingList getInstance() {
        return uniqueInstance;
    }

    public List<ShoppingListFoodItem> getShoppingListFoodItems() {
        return foodItems;
    }

    public void setShoppingListFoodItems(List<ShoppingListFoodItem> shoppingListFoodItems) {
        this.foodItems = shoppingListFoodItems;
    }

    public List<ShoppingListNonFoodItem> getNonFoodItems() {
        return nonFoodItems;
    }

    public void setNonFoodItems(List<ShoppingListNonFoodItem> nonFoodItems) {
        this.nonFoodItems = nonFoodItems;
    }


}
