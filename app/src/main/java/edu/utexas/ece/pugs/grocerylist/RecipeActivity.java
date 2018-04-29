package edu.utexas.ece.pugs.grocerylist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration;
import com.mashape.p.spoonacularrecipefoodnutritionv1.SpoonacularAPIClient;
import com.mashape.p.spoonacularrecipefoodnutritionv1.controllers.APIController;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.APICallBack;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.HttpContext;
import com.mashape.p.spoonacularrecipefoodnutritionv1.models.DynamicResponse;
import com.mashape.p.spoonacularrecipefoodnutritionv1.models.FindByIngredientsModel;
import com.squareup.picasso.Picasso;

import edu.utexas.ece.pugs.grocerylist.foodstuff.Ingredient;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Purchase;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Recipe;
import edu.utexas.ece.pugs.grocerylist.foodstuff.RecipeList;

import static edu.utexas.ece.pugs.grocerylist.RecipeActivity.State.INGREDIENTS;
import static edu.utexas.ece.pugs.grocerylist.RecipeActivity.State.NAME;
import static edu.utexas.ece.pugs.grocerylist.RecipeActivity.State.NUTRIENTS;
import static edu.utexas.ece.pugs.grocerylist.RecipeActivity.State.PANTRY;

/**
 * Created by Brandon on 3/16/2018.
 */

public class RecipeActivity extends AppCompatActivity {

    protected enum State {
        PANTRY, INGREDIENTS, NAME, NUTRIENTS
    }

    State state = PANTRY;
    private TextView mTextMessage;
    String xMashapeKey = "TyI4LJpGVLmshLMmIsnLipUE0L8gp1zPJjKjsn2dx6UOeb2N84";
    CustomAdapter customAdapter = new CustomAdapter();
    Map<String, PantryItem> pantryList = Pantry.getInstance().getPantryItems();
    APIController apiController = APIController.getInstance();
    List<Recipe> recipeList = RecipeList.getInstance().getRecipeList();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
            }
            return false;
        }
    };

    public class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return recipeList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.recipe_details_fragment, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.recipeFragmentImage);
            TextView textViewName = (TextView) view.findViewById(R.id.recipeFragmentName);

            textViewName.setText(recipeList.get(i).getTitle());
            Picasso.get().load(recipeList.get(i).getImage()).into(imageView);

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration.initialize(getApplicationContext());
        SpoonacularAPIClient client = new SpoonacularAPIClient();
        Configuration.setXMashapeKey(xMashapeKey);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), RecipeDetailsActivity.class);
                intent.putExtra("recipe_index", i);
                startActivity(intent);
            }
        });
        listView.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.findRecipesByPantry:
                state = PANTRY;
                String ingredients = null;
                for (String pantryItem : pantryList.keySet()) {
                    PantryItem pantry_item = pantryList.get(pantryItem);
                    Purchase purchase = pantry_item.getPurchases().get(0);
                    if (ingredients == null) {
                        ingredients = purchase.getName();
                    }
                    else {
                        ingredients = ingredients + "," + purchase.getName();
                    }
                }
                findByPantryRecipes(ingredients);
                break;
            case R.id.findRecipesByIngredients:
                state = INGREDIENTS;
                Intent ingredientIntent = new Intent(getApplicationContext(), FindRecipesByIngredientsActivity.class);
                startActivityForResult(ingredientIntent, 1);
                break;
            case R.id.findRecipesByName:
                state = NAME;
                Intent recipeNameIntent = new Intent(getApplicationContext(), FindRecipesByName.class);
                startActivityForResult(recipeNameIntent, 1);
                break;
            case R.id.findRecipesByNutrients:
                state = NUTRIENTS;
                Intent nutrientIntent = new Intent(getApplicationContext(), FindRecipeByNutrientsActivity.class);
                startActivityForResult(nutrientIntent, 1);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                switch (state) {
                    case PANTRY:
                        break;
                    case INGREDIENTS:
                        String ingredients = null;
                        if (!data.getStringExtra("ingredients").isEmpty()) {
                             ingredients = data.getStringExtra("ingredients");
                        }
                        Boolean ingredientLimitLicense = null;
                        if (!data.getStringExtra("ingredientLimitLicense").isEmpty()) {
                            ingredientLimitLicense = Boolean.valueOf(data.getStringExtra("ingredientLimitLicense"));
                        }
                        Integer ingredientNumber = null;
                        if (!data.getStringExtra("ingredientNumber").isEmpty()) {
                            ingredientNumber = Integer.valueOf(data.getStringExtra("ingredientNumber"));
                        }
                        Integer ingredientRanking = null;
                        if (!data.getStringExtra("ingredientRanking").isEmpty()) {
                            ingredientRanking = Integer.valueOf(data.getStringExtra("ingredientRanking"));
                        }
                        findByIngredientsRecipes(ingredients, ingredientLimitLicense, ingredientNumber, ingredientRanking);
                        break;
                    case NAME:
                        String query = null;
                        if (!data.getStringExtra("query").isEmpty()) {
                            query = data.getStringExtra("query");
                        }
                        String cuisine = null;
                        if (!data.getStringExtra("cuisine").isEmpty()) {
                            cuisine = data.getStringExtra("cuisine");
                        }
                        String diet = null;
                        if (!data.getStringExtra("diet").isEmpty()) {
                            diet = data.getStringExtra("diet");
                        }
                        String excludeIngredients = null;
                        if (!data.getStringExtra("excludeIngredients").isEmpty()) {
                            excludeIngredients = data.getStringExtra("excludeIngredients");
                        }
                        String intolerances = null;
                        if (data.getStringExtra("intolerances").isEmpty()) {
                            intolerances = data.getStringExtra("intolerances");
                        }
                        Boolean nameLimitLicense = null;
                        if (!data.getStringExtra("nameLimitLicense").isEmpty()) {
                            nameLimitLicense = Boolean.valueOf(data.getStringExtra("nameLimitLicense"));
                        }
                        Integer nameNumber = null;
                        if (!data.getStringExtra("nameNumber").isEmpty()) {
                            nameNumber = Integer.valueOf(data.getStringExtra("nameNumber"));
                        }
                        Integer offset = null;
                        if(!data.getStringExtra("offset").isEmpty()) {
                            offset = Integer.valueOf(data.getStringExtra("offset"));
                        }
                        String type = null;
                        if (!data.getStringExtra("type").isEmpty()) {
                            type = data.getStringExtra("type");
                        }
                        findByNameRecipes(query, cuisine, diet, excludeIngredients, intolerances, nameLimitLicense, nameNumber, offset, type);
                        break;
                    case NUTRIENTS:
                        Integer maxCalories = null;
                        if (!data.getStringExtra("maxCalories").isEmpty()) {
                            maxCalories = Integer.valueOf(data.getStringExtra("maxCalories"));
                        }
                        Integer maxCarbs = null;
                        if (!data.getStringExtra("maxCarbs").isEmpty()) {
                            maxCarbs = Integer.valueOf(data.getStringExtra("maxCarbs"));
                        }
                        Integer maxFat = null;
                        if (!data.getStringExtra("maxFat").isEmpty()) {
                            maxFat = Integer.valueOf(data.getStringExtra("maxFat"));
                        }
                        Integer maxProtein = null;
                        if (!data.getStringExtra("maxProtein").isEmpty()) {
                            maxProtein = Integer.valueOf(data.getStringExtra("maxProtein"));
                        }
                        Integer minCalories = null;
                        if (!data.getStringExtra("minCalories").isEmpty()) {
                            minCalories = Integer.valueOf(data.getStringExtra("minCalories"));
                        }
                        Integer minCarbs = null;
                        if (!data.getStringExtra("minCalories").isEmpty()) {
                            minCarbs = Integer.valueOf(data.getStringExtra("minCarbs"));
                        }
                        Integer minFat = null;
                        if (!data.getStringExtra("minCalories").isEmpty()) {
                            minFat = Integer.valueOf(data.getStringExtra("minFat"));
                        }
                        Integer minProtein = null;
                        if (!data.getStringExtra("minCalories").isEmpty()) {
                            minProtein = Integer.valueOf(data.getStringExtra("minProtein"));
                        }
                        findByNutrientsRecipes(maxCalories, maxCarbs, maxFat, maxProtein, minCalories, minCarbs, minFat, minProtein);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void findByPantryRecipes(String ingredients) {
        recipeList.clear();
        apiController.findByIngredientsAsync(ingredients, null, null, null, null, new APICallBack<List<FindByIngredientsModel>>() {
            @Override
            public void onSuccess(HttpContext context, List<FindByIngredientsModel> response) {
                findRecipesById(response);
            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                error.printStackTrace();
            }
        });
    }

    private void findByIngredientsRecipes(String ingredients, Boolean ingredientLimitLicense, Integer ingredientNumber, Integer ingredientRanking) {
        recipeList.clear();
        apiController.findByIngredientsAsync(ingredients, ingredientLimitLicense, ingredientNumber,
                ingredientRanking, null, new APICallBack<List<FindByIngredientsModel>>() {
            @Override
            public void onSuccess(HttpContext context, List<FindByIngredientsModel> response) {
                findRecipesById(response);
            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                error.printStackTrace();
            }
        });
    }

    private void findByNutrientsRecipes(Integer maxCalories, Integer maxCarbs, Integer maxFat, Integer maxProtein,
                                        Integer minCalories, Integer minCarbs, Integer minFat, Integer minProtein) {
        recipeList.clear();
        apiController.findByNutrientsAsync(maxCalories, maxCarbs, maxFat, maxProtein,
                minCalories, minCarbs, minFat, minProtein, null, new APICallBack<DynamicResponse>() {
            @Override
            public void onSuccess(HttpContext context, DynamicResponse response) {
                try {
                    ArrayList<Map<String, Object>> dataList = response.parse(ArrayList.class);
                    List<FindByIngredientsModel> recipeResponse = new ArrayList<>();
                    for (Map<String, Object> map : dataList) {
                        FindByIngredientsModel findByIngredientsModel = new FindByIngredientsModel();
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            if (entry.getKey() == "id") {
                                findByIngredientsModel.setId(Integer.valueOf(entry.getValue().toString()));
                            }
                            else if (entry.getKey() == "title") {
                                findByIngredientsModel.setTitle(entry.getValue().toString());
                            }
                            else if (entry.getKey() == "image") {
                                findByIngredientsModel.setImage(entry.getValue().toString());
                            }
                            else {
                                continue;
                            }
                        }
                        recipeResponse.add(findByIngredientsModel);
                    }
                    findRecipesById(recipeResponse);
                }
                catch (java.text.ParseException exception) {
                    exception.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                error.printStackTrace();
            }
        });
    }

    private void findByNameRecipes(String query, String cuisine, String diet, String excludeIngredients, String intolerances,
                                   Boolean nameLimitLicense, Integer nameNumber, Integer offset, String type) {
        recipeList.clear();
        apiController.searchRecipesAsync(query, cuisine, diet, excludeIngredients, intolerances,
                nameLimitLicense, nameNumber, offset, type, null, new APICallBack<DynamicResponse>() {
                    @Override
                    public void onSuccess(HttpContext context, DynamicResponse response) {
                        try {
                            Map<String, Object> dataMap = response.parseAsDictionary();
                            List<FindByIngredientsModel> recipeResponse = new ArrayList<>();
                            if (dataMap.containsKey("results")) {
                                for (Object object : (ArrayList<Object>) dataMap.get("results")) {
                                    Map<String, Object> entryMap = (Map<String, Object>) object;
                                    FindByIngredientsModel findByIngredientsModel = new FindByIngredientsModel();
                                    for (Map.Entry<String, Object> entry : entryMap.entrySet()) {
                                        if (entry.getKey() == "id") {
                                            findByIngredientsModel.setId(Integer.valueOf(entry.getValue().toString()));
                                        }
                                        else if (entry.getKey() == "title") {
                                            findByIngredientsModel.setTitle(entry.getValue().toString());
                                        }
                                        else if (entry.getKey() == "image") {
                                            findByIngredientsModel.setImage(entry.getValue().toString());
                                        }
                                        else {
                                            continue;
                                        }
                                    }
                                    recipeResponse.add(findByIngredientsModel);
                                }
                            }
                            findRecipesById(recipeResponse);
                        }
                        catch(java.text.ParseException exception) {
                            exception.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(HttpContext context, Throwable error) {
                        error.printStackTrace();
                    }
                });
    }

    public void findRecipesById(List<FindByIngredientsModel> response) {
        for (FindByIngredientsModel ingredientsModel : response) {
            apiController.getRecipeInformationAsync(ingredientsModel.getId(), new APICallBack<DynamicResponse>() {
                @Override
                public void onSuccess(HttpContext context, DynamicResponse response) {
                    try {
                        Recipe recipe = new Recipe();
                        List<Ingredient> ingredientsList = new ArrayList<>();
                        Map<String, Object> dataMap = response.parseAsDictionary();
                        if (dataMap.containsKey("id")) {
                            recipe.setId((String.valueOf(dataMap.get("id").toString())));
                        }
                        if (dataMap.containsKey("title")) {
                            recipe.setTitle((String) dataMap.get("title"));
                        }
                        if (dataMap.containsKey("image")) {
                            recipe.setImage((String) dataMap.get("image"));
                        }
                        if (dataMap.containsKey("instructions")) {
                            recipe.setInstructions((String) dataMap.get("instructions"));
                        }
                        if (dataMap.containsKey("extendedIngredients")) {
                            for (Object object : (ArrayList<Object>) dataMap.get("extendedIngredients")) {
                                Ingredient ingredient = new Ingredient();
                                Quantity quantity = new Quantity();
                                Map<String, Object> entryMap = (Map<String, Object>) object;
                                for (Map.Entry<String, Object> entry : entryMap.entrySet()) {
                                    if (entry.getKey() == "id") {
                                        ingredient.setId(entry.getValue().toString());
                                    } else if (entry.getKey() == "aisle") {
                                        ingredient.setAisle(entry.getValue().toString());
                                    } else if (entry.getKey() == "image") {
                                        ingredient.setAisle(entry.getValue().toString());
                                    } else if (entry.getKey() == "name") {
                                        ingredient.setName(entry.getValue().toString());
                                    } else if (entry.getKey() == "amount") {
                                        quantity.setAmount((int) Double.parseDouble(entry.getValue().toString()));
                                    } else if (entry.getKey() == "unit") {
                                        quantity.setUnit(entry.getValue().toString());
                                    } else if (entry.getKey() == "unitShort") {
                                        quantity.setUnitShort(entry.getValue().toString());
                                    } else if (entry.getKey() == "unitLong") {
                                        quantity.setUnitLong(entry.getValue().toString());
                                    } else if (entry.getKey() == "originalString") {
                                        ingredient.setOriginal(entry.getValue().toString());
                                    } else {
                                        continue;
                                    }
                                }
                                ingredient.setQuantity(quantity);
                                ingredientsList.add(ingredient);
                            }
                            recipe.setIngredients(ingredientsList);
                        }
                        recipeList.add(recipe);
                        customAdapter.notifyDataSetChanged();
                        System.out.println("ID/" + recipe.getId());
                        System.out.println("Title/" + recipe.getTitle());
                        System.out.println("Image/" + recipe.getImage());
                        System.out.println("Instructions/" + recipe.getInstructions());
                        for (Ingredient ingredient : recipe.getIngredients()) {
                            System.out.println("Ingredients/" + ingredient.getName());
                        }
                        System.out.println();
                    }
                    catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(HttpContext context, Throwable error) {
                    error.printStackTrace();
                }
            });
        }
    }

}
