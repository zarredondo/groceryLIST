package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Ingredient;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Purchase;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Recipe;
import edu.utexas.ece.pugs.grocerylist.foodstuff.RecipeList;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListFoodItem;

/**
 * Created by Brandon on 3/17/2018.
 */

public class RecipeDetailsActivity extends AppCompatActivity {
    List<Recipe> recipeList = RecipeList.getInstance().getRecipeList();
    Map<String, PantryItem> pantryItemMap = Pantry.getInstance().getPantryItems();
    Map<String, ShoppingListFoodItem> shoppingListFoodItems = ShoppingList.getInstance().getFoodItems();

    ImageView recipeDetailsImage;
    TextView recipeDetailsName;
    TextView recipeDetailsIngredients;
    TextView recipeDetailsInstructions;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        recipeDetailsImage = (ImageView) findViewById(R.id.recipeDetailsImage);
        recipeDetailsName = (TextView) findViewById(R.id.recipeDetailsName);
        recipeDetailsIngredients = (TextView) findViewById(R.id.recipeDetailsIngredients);
        recipeDetailsInstructions = (TextView) findViewById(R.id.recipeDetailsInstructions);

        Intent intent = getIntent();
        final int index = intent.getIntExtra("recipe_index", 0);

        Picasso.get().load(recipeList.get(index).getImage()).into(recipeDetailsImage);
        recipeDetailsName.setText(recipeList.get(index).getTitle());
        for (Ingredient ingredient : recipeList.get(index).getIngredients()) {
            recipeDetailsIngredients.append(ingredient.getName() + "\n");
        }
        recipeDetailsInstructions.setText(recipeList.get(index).getInstructions());

        Button sendToGroceryButton = (Button) findViewById(R.id.sendToGroceryButton);
        sendToGroceryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Ingredient ingredient : recipeList.get(index).getIngredients()) {
                    PantryItem pantryItem = pantryItemMap.get(ingredient.getId());
                    int quantityHolder = 0;
                    for (Purchase purchase : pantryItem.getPurchases()) {
                        quantityHolder = quantityHolder + purchase.getQuantity().getAmount();
                    }
                    if (quantityHolder < ingredient.getQuantity().getAmount()) {
                        ShoppingListFoodItem shoppingListFoodItem = new ShoppingListFoodItem();
                        shoppingListFoodItem.setId(ingredient.getId());
                        shoppingListFoodItem.setAisle(ingredient.getAisle());
                        shoppingListFoodItem.setImage(ingredient.getImage());
                        shoppingListFoodItem.setName(ingredient.getName());
                        shoppingListFoodItem.setConsistency(ingredient.getConsistency());
                        shoppingListFoodItem.setOriginal(ingredient.getOriginal());
                        Quantity quantity = ingredient.getQuantity();
                        quantity.setAmount(quantityHolder - ingredient.getQuantity().getAmount());
                        shoppingListFoodItem.setQuantity(quantity);
                        shoppingListFoodItem.setAddedDate(null);
                        shoppingListFoodItem.setPurchaseDate(null);
                        shoppingListFoodItem.setExpirationDate(null);
                        shoppingListFoodItems.put(shoppingListFoodItem.getId(), shoppingListFoodItem);
                    }
                }
            }
        });
    }
}
