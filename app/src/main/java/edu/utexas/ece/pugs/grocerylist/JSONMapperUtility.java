package edu.utexas.ece.pugs.grocerylist;

/**
 * Created by zarredondo on 4/16/2018.
 */

import org.json.JSONException;
import org.json.JSONObject;

public class JSONMapperUtility {
    private static JSONMapperUtility uniqueInstance = new JSONMapperUtility();

    public static JSONMapperUtility getInstance() {
        return uniqueInstance;
    }

    public String convertFood(Food food) throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("id", food.getId());
        obj.put("name", food.getName());

        return obj.toString();
    }

    public String convertQuantity(Quantity quantity) throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("amount", quantity.amount);
        obj.put("unit", quantity.unit);
        obj.put("unitShort", quantity.unitShort);
        obj.put("unitLong", quantity.unitLong);

        return obj.toString();
    }

    public String convertIngredient(Ingredient ingredient) throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("food", convertFood(ingredient.getFood()));
        obj.put("quantity", convertQuantity(ingredient.getQuantity()));

        return obj.toString();
    }
}
