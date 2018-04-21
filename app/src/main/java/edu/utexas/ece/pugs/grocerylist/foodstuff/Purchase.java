package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zarredondo on 4/20/2018.
 */

public class Purchase extends FoodItem {
    Date purchaseDate;
    Date expirationDate;

    public Purchase(Date expirationDate) {
        this.expirationDate = expirationDate;
        this.purchaseDate = Calendar.getInstance().getTime();
    }

    public Purchase() {
    }

    public Purchase(int id, String original, String name, Quantity quantity, String consistency,
                    String[] shoppingListUnits, String aisle, String image, String[] meta,
                    Date expirationDate) {
        super(id, original, name, quantity, consistency, shoppingListUnits, aisle, image, meta);
        this.expirationDate = expirationDate;
    }
}
