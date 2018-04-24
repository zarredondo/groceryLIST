package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ListFoodItem extends FoodItem {
    private Date addedDate;
    private Date purchaseDate;
    private Date expirationDate;

    public ListFoodItem(String id, String original, String name, Quantity quantity, String consistency,
                    List<String> shoppingListUnits, String aisle, String image, List<String> meta,
                    Date expirationDate) {
        super(id, original, name, quantity, consistency, shoppingListUnits, aisle, image, meta);
        this.expirationDate = expirationDate;
        this.addedDate = Calendar.getInstance().getTime();
    }


    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
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

    public Purchase purchaseItem() {
        return new Purchase(this);
    }

}
