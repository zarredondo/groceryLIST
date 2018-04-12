package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import java.util.List;

/**
 * Created by zarredondo on 4/11/2018.
 */

public class ApplicationManager {
    private static ApplicationManager uniqueInstance = new ApplicationManager();

    List<Intent> intents;

    private ApplicationManager() {
        /*uniqueInstance.addIntent(new Intent(StartActivity.this, PantryActivity.class));
        uniqueInstance.addIntent(new Intent(StartActivity.this, GroceryListActivity.class));
        uniqueInstance.addIntent(new Intent(StartActivity.this, RecipeActivity.class));*/
    };

    public static ApplicationManager getInstance() {
        return uniqueInstance;
    }

    public void setIntents(List<Intent> intents) {
        this.intents = intents;
    }

    public List<Intent> getIntents() {
        return intents;
    }

    public void addIntent(Intent intent) {
        this.intents.add(intent);
    }
}
