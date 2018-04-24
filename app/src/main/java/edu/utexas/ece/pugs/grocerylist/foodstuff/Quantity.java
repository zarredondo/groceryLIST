package edu.utexas.ece.pugs.grocerylist.foodstuff;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class Quantity {
    protected int amount;
    protected String unit;
    protected String unitShort;
    protected String unitLong;

    /* Constructor */
    public Quantity() {
    }

    public Quantity(int amount, String unit, String unitShort, String unitLong) {
        this.amount = amount;
        this.unit = unit;
        this.unitShort = unitShort;
        this.unitLong = unitLong;
    }

    /* Getters and Setters */
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String unitShort) {
        this.unitShort = unitShort;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String unitLong) {
        this.unitLong = unitLong;
    }
}
