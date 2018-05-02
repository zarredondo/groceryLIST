package edu.utexas.ece.pugs.grocerylist.foodstuff;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by zarredondo on 4/16/2018.
 */

public class User {
    private static User uniqueInstance = new User();

    private String userID;
    private String emailAddress;
    private String displayName;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseReference;
    private DatabaseReference pantryReference;
    private DatabaseReference shoppingListReference;
    private DatabaseReference nonFoodItemListReference;
    private DatabaseReference FoodItemListReference;
    private DatabaseReference userReference;
    private Boolean firebaseEnable;

    public Boolean getFirebaseEnable() {
        return firebaseEnable;
    }

    public void setFirebaseEnable(Boolean firebaseEnable) {
        this.firebaseEnable = firebaseEnable;
    }

    public static User getInstance() {
        return uniqueInstance;
    }

    private User() {
    }

    public void setTriplet(String userID, String emailAddress, String displayName) {
        this.userID = userID;
        this.emailAddress = emailAddress;
        this.displayName = displayName;
        this.updateDatabaseReferences();
    }

    public DatabaseReference getPantryReference() {
        return pantryReference;
    }

    public DatabaseReference getShoppingListReference() {
        return shoppingListReference;
    }

    public DatabaseReference getNonFoodItemListReference() {
        return nonFoodItemListReference;
    }

    public DatabaseReference getFoodItemListReference() {
        return FoodItemListReference;
    }

    public DatabaseReference getFirebaseReference() {
        return firebaseReference;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        this.updateDatabaseReferences();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void addUserToDatabase() {
        this.userReference.setValue(this.userID);
    }

    private void updateDatabaseReferences() {
        if (this.firebaseEnable) {
            this.firebaseReference = FirebaseDatabase.getInstance().getReference();
            this.pantryReference = firebaseReference.child("pantryMaps").child(userID);
            this.shoppingListReference = firebaseReference.child("shoppingLists").child(userID);
            this.nonFoodItemListReference = shoppingListReference.child("nonFoodItemList");
            this.FoodItemListReference = shoppingListReference.child("foodItemList");
            this.userReference = firebaseReference.child("users");
        }
    }

    public void signOut() {
        this.userID = null;
        this.emailAddress = null;
        this.displayName = null;
        this.firebaseReference = null;
        this.pantryReference = null;;
        this.shoppingListReference = null;
        this.nonFoodItemListReference = null;
        this.FoodItemListReference = null;
        this.userReference = null;
    }
}
