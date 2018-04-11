package edu.utexas.ece.pugs.grocerylist;

import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class Pantry {
    protected List<PantryItem> pantryItems;

    public Pantry(List<PantryItem> pantryItems) {
        this.pantryItems = pantryItems;
    }

    public List<PantryItem> getPantryItems() {
        return pantryItems;
    }
}
