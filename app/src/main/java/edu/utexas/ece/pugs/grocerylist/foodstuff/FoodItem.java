package edu.utexas.ece.pugs.grocerylist.foodstuff;

/**
 * Created by zarredondo on 4/20/2018.
 */

public class FoodItem {
    int id;
    String original;
    String name;
    Quantity quantity;
    String consistency;
    String[] shoppingListUnits;
    String aisle;
    String image;
    String[] meta;

    public FoodItem() {

    }

    public FoodItem(int id, String original, String name, Quantity quantity, String consistency,
                    String[] shoppingListUnits, String aisle, String image, String[] meta) {
        this.id = id;
        this.original = original;
        this.name = name;
        this.quantity = quantity;
        this.consistency = consistency;
        this.shoppingListUnits = shoppingListUnits;
        this.aisle = aisle;
        this.image = image;
        this.meta = meta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public String getConsistency() {
        return consistency;
    }

    public void setConsistency(String consistency) {
        this.consistency = consistency;
    }

    public String[] getShoppingListUnits() {
        return shoppingListUnits;
    }

    public void setShoppingListUnits(String[] shoppingListUnits) {
        this.shoppingListUnits = shoppingListUnits;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getMeta() {
        return meta;
    }

    public void setMeta(String[] meta) {
        this.meta = meta;
    }
}
