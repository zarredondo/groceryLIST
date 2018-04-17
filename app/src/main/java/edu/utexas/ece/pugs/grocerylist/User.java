package edu.utexas.ece.pugs.grocerylist;

/**
 * Created by zarredondo on 4/16/2018.
 */

public class User {
    String emailAddress;
    String displayName;

    public User() {
    }

    public User(String emailAddress, String displayName) {
        this.emailAddress = emailAddress;
        this.displayName = displayName;
    }
}
