package edu.utexas.ece.pugs.grocerylist.foodstuff;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    private static ShoppingList uniqueInstance = new ShoppingList();

    private Map<String, ShoppingListFoodItem> foodItems;

    private List<ShoppingListNonFoodItem> nonFoodItems;

    public static ShoppingList getInstance() {
        return uniqueInstance;
    }

    private ShoppingList() {
        nonFoodItems = new ArrayList<>();
        foodItems = new HashMap<>();
    }

    public void addItem(ShoppingListFoodItem food){
        if(!foodItems.containsKey(food.getId())){
            foodItems.put(food.getId(), food);
        }
        if (User.getInstance().getFirebaseEnable()) {
            User.getInstance().getFoodItemListReference().setValue(foodItems);
        }
    }

    public void addItem(ShoppingListNonFoodItem nonFood){
        int containsFlag = 0;
        for(int i = 0; i < nonFoodItems.size(); i++) {
            if (nonFoodItems.get(i).getName().compareTo(nonFood.getName()) == 0) {
                containsFlag = 1;
            }
        }
        if (containsFlag == 0){
            nonFoodItems.add(nonFood);
        }
        if (User.getInstance().getFirebaseEnable()) {
            User.getInstance().getNonFoodItemListReference().setValue(nonFoodItems);
        }
    }

    public void removeItem(String key){
        if(foodItems.containsKey(key)){
            foodItems.remove(key);
        }
    }

    public Map<String, ShoppingListFoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(Map<String, ShoppingListFoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public List<ShoppingListNonFoodItem> getNonFoodItems() {
        return nonFoodItems;
    }

    public void setNonFoodItems(List<ShoppingListNonFoodItem> nonFoodItems) {
        this.nonFoodItems = nonFoodItems;
    }

}
