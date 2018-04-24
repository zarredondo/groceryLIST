package edu.utexas.ece.pugs.grocerylist.foodstuff;

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
    private DatabaseReference firebaseReference;
    private DatabaseReference pantryReference;
    private DatabaseReference shoppingListReference;

    public static User getInstance() {
        return uniqueInstance;
    }

    private User() {
    }

    public void setTriplet(String userID, String emailAddress, String displayName) {
        this.userID = userID;
        this.emailAddress = emailAddress;
        this.displayName = displayName;
        this.firebaseReference = FirebaseDatabase.getInstance().getReference();
        this.pantryReference = firebaseReference.child("pantryMaps").child(userID);
        this.shoppingListReference = firebaseReference.child("shoppingLists").child(userID);
    }

    public String getUserID() {
        return userID;
    }

    public DatabaseReference getPantryReference() {
        return pantryReference;
    }

    public void setPantryReference(DatabaseReference pantryReference) {
        this.pantryReference = pantryReference;
    }

    public DatabaseReference getShoppingListReference() {
        return shoppingListReference;
    }

    public void setShoppingListReference(DatabaseReference shoppingListReference) {
        this.shoppingListReference = shoppingListReference;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        this.firebaseReference = FirebaseDatabase.getInstance().getReference();
        this.pantryReference = firebaseReference.child("pantryMaps").child(userID);
        this.shoppingListReference = firebaseReference.child("shoppingLists").child(userID);
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
}
