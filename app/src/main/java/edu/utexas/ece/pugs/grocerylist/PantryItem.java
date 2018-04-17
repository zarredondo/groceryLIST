package edu.utexas.ece.pugs.grocerylist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class PantryItem {
    protected Food food;
    protected Date purchaseDate;
    protected Date expirationDate;

    public PantryItem() {   
    }

    public PantryItem(Food food, Date purchaseDate, Date expirationDate) {
        this.food = food;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
    }
}
