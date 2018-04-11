package edu.utexas.ece.pugs.grocerylist;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class Quantity {
    protected String amount;
    protected String unit;
    protected String unitShort;
    protected String unitLong;

    /* Constructor */
    public Quantity(String amount, String unit, String unitShort, String unitLong) {
        this.amount = amount;
        this.unit = unit;
        this.unitShort = unitShort;
        this.unitLong = unitLong;
    }

    /* Getters and Setters */
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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
