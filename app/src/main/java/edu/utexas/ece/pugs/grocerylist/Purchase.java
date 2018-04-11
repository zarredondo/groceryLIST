package edu.utexas.ece.pugs.grocerylist;

import java.util.Date;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class Purchase {
    protected Quantity quantity;
    protected Date purchaseDate;
    protected Date expirationDate;

    public Purchase(Quantity quantity, Date purchaseDate, Date expirationDate) {
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
    }

}
