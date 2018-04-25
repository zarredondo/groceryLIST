package edu.utexas.ece.pugs.grocerylist.SpoonacularControllers;

import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.APICallBack;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.HttpContext;
import com.mashape.p.spoonacularrecipefoodnutritionv1.models.DynamicResponse;
import com.mashape.p.spoonacularrecipefoodnutritionv1.models.Productjson;

import java.text.ParseException;
import java.util.Map;

/**
 * Created by tater on 4/12/2018.
 */

public class DynamicCallBack implements APICallBack<DynamicResponse> {

    private Map<String, Object> result;

    public Map<String, Object> getResult() {
        return result;
    }


    @Override
    public void onSuccess(HttpContext context, DynamicResponse response) {
        try {
            result = response.parseAsDictionary();
        } catch (ParseException e) {
            result.put("error", "There was an issue adding items. Please try again later");
        }

    }

    @Override
    public void onFailure(HttpContext context, Throwable error) {
        result.put("error", "There was an issue adding items. Please try again later");
    }
}
