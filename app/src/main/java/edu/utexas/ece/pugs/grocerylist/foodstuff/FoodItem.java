package edu.utexas.ece.pugs.grocerylist.foodstuff;

import java.util.List;
import java.util.Map;

/**
 * Created by zarredondo on 4/20/2018.
 */

public class FoodItem {
    String id;
    String original;
    String name;
    Quantity quantity;
    String consistency;
    String aisle;
    String image;

    public FoodItem() {

    }

    public FoodItem(Map<String, Object> itemEntry) {
        this.id = (String)itemEntry.get("id");
        this.name = (String)itemEntry.get("name");
        this.original = (String)itemEntry.get("original");
        this.quantity.setAmount((int)itemEntry.get("amount"));
        if (itemEntry.containsKey("unit")){
            this.quantity.setUnit((String)itemEntry.get("unit"));
        }
        else if (itemEntry.containsKey("unitLong")) {
            this.quantity.setUnit((String)itemEntry.get("unitLong"));
        }
        this.image = (String)itemEntry.get("image");

    }

    public FoodItem(String id, String original, String name, Quantity quantity, String consistency, String aisle, String image) {
        this.id = id;
        this.original = original;
        this.name = name;
        this.quantity = quantity;
        this.consistency = consistency;
        this.aisle = aisle;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}
