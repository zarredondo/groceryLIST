package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zarredondo on 4/20/2018.
 */

public class Purchase extends FoodItem {
    Date purchaseDate;
    Date expirationDate;

    public Purchase() {
    }

    public Purchase(String id, String original, String name, Quantity quantity, String consistency,
                    List<String> shoppingListUnits, String aisle, String image, List<String> meta,
                    Date expirationDate) {
        super(id, original, name, quantity, consistency, shoppingListUnits, aisle, image, meta);
        this.expirationDate = expirationDate;
        this.purchaseDate = Calendar.getInstance().getTime();
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
