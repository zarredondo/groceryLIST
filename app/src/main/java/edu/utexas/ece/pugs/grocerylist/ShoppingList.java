package edu.utexas.ece.pugs.grocerylist;

import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ShoppingList {
    protected List<ShoppingListItem> listItems;

    public ShoppingList(List<ShoppingListItem> listItems) {
        this.listItems = listItems;
    }

    public List<ShoppingListItem> getShoppingListItems() {
        return listItems;
    }
}
