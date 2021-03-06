package edu.utexas.ece.pugs.grocerylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.graphics.drawable.*;
import android.graphics.*;
import android.widget.EditText;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration;
import com.mashape.p.spoonacularrecipefoodnutritionv1.SpoonacularAPIClient;
import com.mashape.p.spoonacularrecipefoodnutritionv1.controllers.APIController;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.APICallBack;
import com.mashape.p.spoonacularrecipefoodnutritionv1.http.client.HttpContext;
import com.mashape.p.spoonacularrecipefoodnutritionv1.models.DynamicResponse;


import org.json.JSONException;
import org.json.JSONObject;

import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Purchase;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

public class PantryActivity extends BaseActivity {

    ArrayAdapter<String> adapter;
    ListView lstItems;
    final String XMashapeKey = "TyI4LJpGVLmshLMmIsnLipUE0L8gp1zPJjKjsn2dx6UOeb2N84";
    SpoonacularAPIClient client;
    APIController controller;
    public ArrayList<Map<String, Object>> result;
    public Map<String, PantryItem> itemMap;
    public String item;
    public Pantry pan;
    public User user;
    private Drawable bckgrnd;

    LinearLayout dynamicContent, bottonNavBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.mashape.p.spoonacularrecipefoodnutritionv1.Configuration.initialize(this);
        client = new SpoonacularAPIClient();
        Configuration.setXMashapeKey(XMashapeKey);
        //setContentView(R.layout.activity_pantry);

        dynamicContent = (LinearLayout)  findViewById(R.id.dynamicContent);
        bottonNavBar= (LinearLayout) findViewById(R.id.bottonNavBar);
        View wizard = getLayoutInflater().inflate(R.layout.activity_pantry, null);
        dynamicContent.addView(wizard);


        //get the reference of RadioGroup.

        RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton rb=(RadioButton)findViewById(R.id.pantry);

        // Change the corresponding icon and text color on nav button click.

        rb.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.ic_pantry, 0,0);
        rb.setTextColor(Color.parseColor("#3F51B5"));


        Configuration.initialize(getApplicationContext());

        controller = client.getClient();
        lstItems = (ListView) findViewById(R.id.lstItems);

        pan = Pantry.getInstance();
        user = User.getInstance();

        user.getPantryReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemMap = new HashMap<String, PantryItem>();
                ArrayList<String> itemList = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    PantryItem pan = ds.getValue(PantryItem.class);
                    itemMap.put(ds.getKey(), pan);
                }
                ArrayList<PantryItem> values = new ArrayList<>(itemMap.values());
                for(PantryItem items : values){
                    item = items.getItemName();
                    itemList.add(item);
                }
                Collections.sort(itemList);
                showItemList(itemList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bckgrnd = getDrawable(R.drawable.pantry_background);
            getWindow().setBackgroundDrawable(bckgrnd);
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    private void showItemList(ArrayList<String> itemList){
        if(adapter == null){
            adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.item_title, itemList);
            lstItems.setAdapter(adapter);
        }
        else{
            adapter.clear();
            adapter.addAll(itemList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_add_item:
                final EditText itemEditText = new EditText(this);
                //itemEditText.setHint("Qty, Weight, product");
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Pantry Item")
                        .setMessage("Qty Weight Item")
                        .setView(itemEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int chich){
                                final String item = String.valueOf(itemEditText.getText());
                                controller.createParseIngredientsAsync(item, 2, new APICallBack<DynamicResponse>() {
                                    @Override
                                    public void onSuccess(HttpContext context, DynamicResponse response) {
                                        try {
                                            result = response.parse(ArrayList.class);
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                            System.out.println("error1");
                                        }
                                        Purchase pur = new Purchase();
                                        Quantity quan = new Quantity();
                                        for(Map.Entry<String, Object> entry: result.get(0).entrySet()) {
                                            System.out.println(entry.getValue().toString());
                                            if(entry.getKey() == "id") {
                                                pur.setId(entry.getValue().toString());
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
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteItem(View view){
        View  parent = (View)view.getParent();
        TextView itemTextView = (TextView) parent.findViewById(R.id.item_title);
        final String item = String.valueOf(itemTextView.getText());

        controller.createParseIngredientsAsync(item, 2, new APICallBack<DynamicResponse>() {
            @Override
            public void onSuccess(HttpContext context, DynamicResponse response) {
                String key = "";
                List<Purchase> value = new ArrayList<>();
                try {
                    result = response.parse(ArrayList.class);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for(Map.Entry<String, Object> entry: result.get(0).entrySet()) {
                    if(entry.getKey() == "id") {
                        key = entry.getValue().toString();
                        System.out.println(key);
                    }
                    else{
                    }
                }
                //key = id
                pan.removePurchase(key);
                user.getPantryReference().child(key).removeValue();
            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {

            }
        });
    }

    public void updateItem(View view){
        View  parent = (View)view.getParent();
        TextView itemTextView = (TextView) parent.findViewById(R.id.item_title);
        final String item = String.valueOf(itemTextView.getText());

        controller.createParseIngredientsAsync(item, 2, new APICallBack<DynamicResponse>() {
            @Override
            public void onSuccess(HttpContext context, DynamicResponse response) {
                String key = "";
                List<Purchase> value = new ArrayList<>();
                try {
                    result = response.parse(ArrayList.class);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                for(Map.Entry<String, Object> entry: result.get(0).entrySet()) {
                    if(entry.getKey() == "id") {
                        key = entry.getValue().toString();
                        System.out.println(key);
                    }
                    else{
                    }
                }
                //key = id
                pan.updatePurchase(key);
            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {

            }
        });
    }

}
