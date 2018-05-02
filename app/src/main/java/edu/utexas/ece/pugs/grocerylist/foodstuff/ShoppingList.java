package edu.utexas.ece.pugs.grocerylist.foodstuff;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    private static ShoppingList uniqueInstance = new ShoppingList();

    private List<ShoppingListNonFoodItem> nonFoodItems;
    private List<ShoppingListFoodItem> foodItems;

    private ShoppingList() {
        nonFoodItems = new ArrayList<ShoppingListNonFoodItem>();
        foodItems = new ArrayList<ShoppingListFoodItem>();

        User.getInstance().getNonFoodItemListReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    ShoppingListNonFoodItem item = ds.getValue(ShoppingListNonFoodItem.class);
                    nonFoodItems.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        User.getInstance().getFoodItemListReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    ShoppingListFoodItem item = ds.getValue(ShoppingListFoodItem.class);
                    foodItems.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
        for(Object m : foodItems){
            HashMap<String, Object> n = (HashMap<String, Object>) m;

            if( key.compareTo( (String) n.get("name")) == 0) {
                x = foodItems.indexOf(m);
                foodItems.remove(m);
                User.getInstance().getFoodItemListReference().setValue(foodItems);
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

    public boolean contains(String name){
        for (int i = 0; i < foodItems.size(); i++){
            Object e = foodItems.get(i);
            HashMap<String, Object> f = (HashMap<String, Object>) e;

            if (   name.compareTo((String)f.get("name"))  == 0  )
                return true;
        }
        return false;
    }
}
