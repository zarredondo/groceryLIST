package edu.utexas.ece.pugs.grocerylist;

import android.app.Activity;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.mashape.p.spoonacularrecipefoodnutritionv1.controllers.APIController;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.APICallBack;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.HttpContext;
import com.mashape.p.spoonacularrecipefoodnutritionv1.models.DynamicResponse;
import com.mashape.p.spoonacularrecipefoodnutritionv1.models.FindByIngredientsModel;

/**
 * Created by Brandon on 3/16/2018.
 */

public class RecipeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    APIController apiController = APIController.getInstance();
    List<Recipe> recipeList = RecipeList.getInstance().getRecipeList();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
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

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
            TextView textViewInstructions = (TextView) view.findViewById(R.id.textViewInstructions);

            //imageView.setImageResource(RecipeList.get(i).getImage());
            textViewName.setText(recipeList.get(i).getName());
            textViewInstructions.setText(recipeList.get(i).getPercentage());

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
        CustomAdapter customAdapter = new CustomAdapter();
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
                break;
            case R.id.findRecipesByIngredients:
                break;
            case R.id.findRecipesByName:
                break;
            case R.id.findRecipesByNutrients:
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void findByIngredientsRecipes(String ingredients) {
        apiController.findByIngredientsAsync("get from ingredient inputs", null, null, null, null, new APICallBack<List<FindByIngredientsModel>>() {
            @Override
            public void onSuccess(HttpContext context, List<FindByIngredientsModel> response) {
                for (FindByIngredientsModel ingredientsModel : response) {
                    apiController.getRecipeInformationAsync(ingredientsModel.getId(), new APICallBack<DynamicResponse>() {
                        @Override
                        public void onSuccess(HttpContext context, DynamicResponse response) {
                            Recipe recipe = new Recipe();
                            try {
                                recipe.setInstructions(response.parseAsString());
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

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                error.printStackTrace();
            }
        });
    }

    private void findByPantryRecipes(List<Recipe> RecipeList) {
        apiController.findByIngredientsAsync("get from pantry", null, null, null, null, new APICallBack<List<FindByIngredientsModel>>() {
            @Override
            public void onSuccess(HttpContext context, List<FindByIngredientsModel> response) {
                for (FindByIngredientsModel ingredientsModel : response) {
                    apiController.getRecipeInformationAsync(ingredientsModel.getId(), new APICallBack<DynamicResponse>() {
                        @Override
                        public void onSuccess(HttpContext context, DynamicResponse response) {
                            Recipe recipe = new Recipe();
                            try {
                                recipe.setInstructions(response.parseAsString());
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

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                error.printStackTrace();
            }
        });
    }

    private void findByNutrientsRecipes(List<Recipe> RecipeList) {
        apiController.findByNutrientsAsync(null, null, null, null, null, null, null, null, null, new APICallBack<DynamicResponse>() {
            @Override
            public void onSuccess(HttpContext context, DynamicResponse response) {

            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                error.printStackTrace();
            }
        });
    }

    private void findByNameRecipes(List<Recipe> RecipeList) {
        apiController.findByIngredientsAsync("get from pantry", null, null, null, null, new APICallBack<List<FindByIngredientsModel>>() {
            @Override
            public void onSuccess(HttpContext context, List<FindByIngredientsModel> response) {
                for (FindByIngredientsModel ingredientsModel : response) {
                    apiController.getRecipeInformationAsync(ingredientsModel.getId(), new APICallBack<DynamicResponse>() {
                        @Override
                        public void onSuccess(HttpContext context, DynamicResponse response) {
                            Recipe recipe = new Recipe();
                            try {
                                recipe.setInstructions(response.parseAsString());
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

            @Override
            public void onFailure(HttpContext context, Throwable error) {
                error.printStackTrace();
            }
        });
    }

}
