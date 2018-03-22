package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by Brandon on 3/16/2018.
 */

public class RecipeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    List<Recipe> RecipeList = new ArrayList<>();

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
            return RecipeList.size();
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
            //TODO FIND IMAGES TO PUT IN
            //ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
            TextView textViewInstructions = (TextView) view.findViewById(R.id.textViewInstructions);

            //imageView.setImageResource(RecipeList.get(i).getImage());
            textViewName.setText(RecipeList.get(i).getName());
            textViewInstructions.setText(RecipeList.get(i).getPercentage());

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        findRecipes(RecipeList);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO REIMPLEMENT AS A FRAGMENT, NEW ACTIVITY KIND OF CLUNKY
                Intent intent = new Intent(RecipeActivity.this, PantryActivity.class);
                intent.putExtra("image", RecipeList.get(i).getImage());
                intent.putExtra("name", RecipeList.get(i).getName());
                intent.putExtra("percentage", RecipeList.get(i).getPercentage());
                intent.putExtra("instructions", RecipeList.get(i).getInstructions());
                startActivity(intent);
            }
        });
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    private void findRecipes(List<Recipe> RecipeList) {
        //TODO GRAB RECIPES FROM DATABASE
        for (int i = 0; i < 10; i++) {
            //MAKESHIFT RECIPE LIST FOR TESTING
            String name = "Food";
            String percentage = Integer.toString(80);
            String instructions = "Slice them";
            List<String> ingredients = new ArrayList<>();
            ingredients.add("Tomato");
            ingredients.add("Potato");
            RecipeList.add(new Recipe(name, percentage, instructions, ingredients));
        }
    }

    private void sortRecipes(List<Recipe> RecipeList) {
        //TODO IMPLEMENT SORTING FUNCTIONS
    }
}
