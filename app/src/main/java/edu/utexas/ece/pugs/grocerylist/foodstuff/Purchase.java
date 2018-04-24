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
                    String aisle, String image, Date expirationDate) {
        super(id, original, name, quantity, consistency, aisle, image);
        this.expirationDate = expirationDate;
        this.purchaseDate = Calendar.getInstance().getTime();
    }

    public Purchase(ShoppingListFoodItem listFoodItem) {
        super(listFoodItem.getId(), listFoodItem.getOriginal(), listFoodItem.getName(),
                listFoodItem.getQuantity(), listFoodItem.getConsistency(),
                listFoodItem.getAisle(), listFoodItem.getImage());
        this.expirationDate = listFoodItem.getExpirationDate();
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
