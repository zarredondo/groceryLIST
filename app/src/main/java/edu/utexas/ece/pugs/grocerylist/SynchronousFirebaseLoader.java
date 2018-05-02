//package edu.utexas.ece.pugs.grocerylist;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
//import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
//import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;
//import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListFoodItem;
//import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListNonFoodItem;
//import edu.utexas.ece.pugs.grocerylist.foodstuff.User;
//
///**
// * Created by zarredondo on 4/25/2018.
// */
//
//public class SynchronousFirebaseLoader {
//    public void execute() {
//        User.getInstance().getPantryReference().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Map<String, PantryItem> pantryItemMap = (Map<String, PantryItem>) dataSnapshot.getValue();
//                if (pantryItemMap == null) {
//                    Pantry.getInstance().setPantryItems(new HashMap<String, PantryItem>());
//                } else {
//                    Pantry.getInstance().setPantryItems(pantryItemMap);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        User.getInstance().getFoodItemListReference().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                List<ShoppingListFoodItem> foodItems = (ArrayList<ShoppingListFoodItem>) dataSnapshot.getValue();
//                if (foodItems == null) {
//                    ShoppingList.getInstance().setShoppingListFoodItems(new ArrayList<ShoppingListFoodItem>());
//                } else {
//                    ShoppingList.getInstance().setShoppingListFoodItems(foodItems);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        User.getInstance().getNonFoodItemListReference().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                List<ShoppingListNonFoodItem> nonFoodItems = (ArrayList<ShoppingListNonFoodItem>) dataSnapshot.getValue();
//                if (nonFoodItems == null) {
//                    ShoppingList.getInstance().setNonFoodItems(new ArrayList<ShoppingListNonFoodItem>());
//                } else {
//                    ShoppingList.getInstance().setNonFoodItems(nonFoodItems);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//}
