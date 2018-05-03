package edu.utexas.ece.pugs.grocerylist;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration;
import com.mashape.p.spoonacularrecipefoodnutritionv1.SpoonacularAPIClient;
import com.mashape.p.spoonacularrecipefoodnutritionv1.controllers.APIController;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.APICallBack;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.HttpContext;
import com.mashape.p.spoonacularrecipefoodnutritionv1.models.DynamicResponse;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.utexas.ece.pugs.grocerylist.foodstuff.FoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Ingredient;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Purchase;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingList;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListNonFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class GroceryListActivity extends BaseActivity {


    private ShoppingList shoppingList;
    private ArrayAdapter<String> adapter;
    private User user;
    final String XMashapeKey = "TyI4LJpGVLmshLMmIsnLipUE0L8gp1zPJjKjsn2dx6UOeb2N84";
    SpoonacularAPIClient client;
    APIController controller;
    public ArrayList<Map<String, Object>> result;
    public ListView lstGrocery;
    private LinearLayout dynamicContent;
    private LinearLayout bottonNavBar;
    public ArrayList<String> listItem;
    public ArrayList<CheckBox> checked;
    public Pantry pan;
    public String key;
    public Map<String, ShoppingListFoodItem> itemMaps;
    private List<ShoppingListNonFoodItem> itemList;
    public String theKey;
    private Drawable bckgrnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        shoppingList = ShoppingList.getInstance();

        super.onCreate(savedInstanceState);
        com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration.initialize(this);
        client = new SpoonacularAPIClient();
        Configuration.setXMashapeKey(XMashapeKey);
        //setContentView(R.layout.activity_grocery_list);

        dynamicContent = (LinearLayout)  findViewById(R.id.dynamicContent);
        bottonNavBar = (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_grocery_list, null);
        dynamicContent.addView(wizard);


        //get the reference of RadioGroup.

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        RadioButton rb = (RadioButton) findViewById(R.id.grocerylist);

        // Change the corresponding icon and text color on nav button click.

        rb.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_cart, 0, 0);
        rb.setTextColor(Color.parseColor("#3F51B5"));

        //Instantiation of APIController and APICallback response system
        com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration.initialize(this);
        client = new SpoonacularAPIClient();
        Configuration.setXMashapeKey(XMashapeKey);
        controller = client.getClient();

        lstGrocery = (ListView) findViewById(R.id.lstGrocery);
        listItem = new ArrayList<>();
        checked = new ArrayList<>();
        pan = Pantry.getInstance();
        key = "";


        user = User.getInstance();
        shoppingList = ShoppingList.getInstance();


        user.getFoodItemListReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemMaps = new HashMap<String, ShoppingListFoodItem>();
                ArrayList<String> grocery = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    ShoppingListFoodItem listFoodItem = ds.getValue(ShoppingListFoodItem.class);
                    itemMaps.put(ds.getKey(), listFoodItem);
                }
                ArrayList<ShoppingListFoodItem> values = new ArrayList<>(itemMaps.values());
                for(ShoppingListFoodItem items : values){
                    theKey = items.getName().toString();
                    grocery.add(theKey);
                }
                Collections.sort(grocery);
                showItemList(grocery);
//
//                ArrayList<String> grocery = new ArrayList<>();
//                ArrayList<ShoppingListFoodItem> itemList = new ArrayList<>();
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    itemList.add(ds.getValue(ShoppingListFoodItem.class));
//                }
//                for(ShoppingListFoodItem it: itemList)
//                    grocery.add(it.getName());
//                showItemList(grocery);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        user.getNonFoodItemListReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemList = new ArrayList<ShoppingListNonFoodItem>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ShoppingListNonFoodItem listNonFoodItem = ds.getValue(ShoppingListNonFoodItem.class);
                    itemList.add(listNonFoodItem);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        final Button addItemButton = findViewById(R.id.addItemButton);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final TextView item = findViewById(R.id.addItemText);
                final String name = String.valueOf(item.getText());
                item.setText("");

                controller.createParseIngredientsAsync(name, 1, new APICallBack<DynamicResponse>() {
                    @Override
                    public void onSuccess(HttpContext context, DynamicResponse response) {
                        try {
                            result = response.parse(ArrayList.class);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ShoppingListFoodItem food = new ShoppingListFoodItem();
                        ShoppingListNonFoodItem notFood = new ShoppingListNonFoodItem();
                        Quantity quan = new Quantity();
                        if(result.get(0).containsKey("aisle")) {
                            for (Map.Entry<String, Object> map : result.get(0).entrySet()) {
                                if (map.getKey() == "id") {
                                    food.setId(map.getValue().toString());
                                } else if (map.getKey() == "original") {
                                    food.setOriginal(map.getValue().toString());
                                } else if (map.getKey() == "name") {
                                    food.setName(map.getValue().toString());
                                } else if (map.getKey() == "amount") {
                                    quan.setAmount((int) Double.parseDouble(map.getValue().toString()));
                                } else if (map.getKey() == "consistency") {
                                    food.setConsistency(map.getValue().toString());
                                } else if (map.getKey() == "aisle") {
                                    food.setAisle(map.getValue().toString());
                                } else if (map.getKey() == "image") {
                                    food.setImage(map.getValue().toString());
                                } else if (map.getKey() == "unit") {
                                    quan.setUnit(map.getValue().toString());
                                } else if (map.getKey() == "unitShort") {
                                    quan.setUnitShort(map.getValue().toString());
                                } else if (map.getKey() == "unitLong") {
                                    quan.setUnitLong(map.getValue().toString());
                                } else {
                                }
                            }
                            food.setExpirationDate(Calendar.getInstance().getTime());
                            food.setPurchaseDate(Calendar.getInstance().getTime());
                            food.setQuantity(quan);
                            shoppingList.addItem(food);
                        }
                        else{
                            notFood.setName(name);
                            Quantity quans = new Quantity(1, "", "", "" );
                            notFood.setQuantity(quans);
                            shoppingList.addItem(notFood);
                        }
                    }

                    @Override
                    public void onFailure(HttpContext context, Throwable error) {

                    }
                });

            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bckgrnd = getDrawable(R.drawable.diet_food_fresh_background);
            getWindow().setBackgroundDrawable(bckgrnd);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }


    protected void onPurchased() {
        //TODO
    }

    private void showItemList(ArrayList<String> itemList){
        if(adapter == null){
            adapter = new ArrayAdapter<String>(this, R.layout.row_grocerylist, R.id.grocery_title, itemList);
            lstGrocery.setAdapter(adapter);
        }
        else{
            adapter.clear();
            adapter.addAll(itemList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grocery_menu, menu);

        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    //--------------needs work---------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.purchased:
                for(String s: listItem) {
                    System.out.println(s);
                    controller.createParseIngredientsAsync(s, 2, new APICallBack<DynamicResponse>() {
                        @Override
                        public void onSuccess(HttpContext context, DynamicResponse response) {
                            try {
                                result = response.parse(ArrayList.class);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            Purchase pur = new Purchase();
                            Quantity quan = new Quantity();
                            for(Map.Entry<String, Object> entry: result.get(0).entrySet()) {
                                System.out.println(entry.getValue().toString());
                                if(entry.getKey() == "id") {
                                    pur.setId(entry.getValue().toString());
                                    key = entry.getValue().toString();
                                    shoppingList.removeItem(key);
                                    user.getFoodItemListReference().child(key).removeValue();
                                }
                                else if(entry.getKey() == "original") {
                                    pur.setOriginal(entry.getValue().toString());
                                }else if(entry.getKey() == "name") {
                                    pur.setName(entry.getValue().toString());
                                }else if(entry.getKey() == "amount") {
                                    quan.setAmount((int) Double.parseDouble(entry.getValue().toString()));
                                }else if(entry.getKey() == "consistency") {
                                    pur.setConsistency(entry.getValue().toString());
                                }else if(entry.getKey() == "aisle") {
                                    pur.setAisle(entry.getValue().toString());
                                }else if(entry.getKey() == "image") {
                                    pur.setImage(entry.getValue().toString());
                                } else if(entry.getKey() == "unit") {
                                    quan.setUnit(entry.getValue().toString());
                                }else if(entry.getKey() == "unitShort") {
                                    quan.setUnitShort(entry.getValue().toString());
                                }else if(entry.getKey() == "unitLong") {
                                    quan.setUnitLong(entry.getValue().toString());
                                }
                                else{
                                }
                            }
                            pur.setExpirationDate(Calendar.getInstance().getTime());
                            pur.setPurchaseDate(Calendar.getInstance().getTime());
                            pur.setQuantity(quan);
                            pan.addPurchase(pur);
                            }

                        @Override
                        public void onFailure(HttpContext context, Throwable error) {

                        }
                    });
                }
                for(CheckBox c : checked){
                    c.toggle();
                }
                checked.clear();
                listItem.clear();

                //TODO here goes the logic after selecting the checkboxes and buying your stuff,
                // in other words, when you click the cart icon
        }
        return super.onOptionsItemSelected(item);
    }


//-----------------------done-----------------------------
    public void deleteGrocery(View view) {
        View parent = (View) view.getParent();
        TextView itemTextView = (TextView) parent.findViewById(R.id.grocery_title);
        final String item = String.valueOf(itemTextView.getText());

        controller.createParseIngredientsAsync(item, 1, new APICallBack<DynamicResponse>() {
            @Override
            public void onSuccess(HttpContext context, DynamicResponse response) {
                String otherKey ="";
                try {
                    result = response.parse(ArrayList.class);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for (Map.Entry<String, Object> map : result.get(0).entrySet()) {
                    if(map.getKey() == "id") {
                        otherKey = map.getValue().toString();
                    }
                }
                shoppingList.removeItem(otherKey);
                user.getFoodItemListReference().child(otherKey).removeValue();

            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {

            }
        });

    }
    //---------------------work to be done-------------------------------
    public void purchaseItem(View view){
        View parent = (View) view.getParent();
        TextView itemTextView = (TextView) parent.findViewById(R.id.grocery_title);
        final String item = String.valueOf(itemTextView.getText());
        if(((CheckBox) view).isChecked()){
            listItem.add(item);
            checked.add((CheckBox) view);
        }
        else if(listItem.contains(item)){
            listItem.remove(item);
            if(checked.contains((CheckBox) view))
                checked.remove((CheckBox) view);
        }
    }

}

