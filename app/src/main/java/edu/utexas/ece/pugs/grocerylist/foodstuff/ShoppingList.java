package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.List;

import edu.utexas.ece.pugs.grocerylist.foodstuff.ListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ListNonFoodItem;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    List<ListFoodItem> listFoodItems;
    List<ListNonFoodItem> nonFoodItems;

    public ShoppingList(List<ListFoodItem> listItems) {
        this.listFoodItems = listItems;
    }



}
