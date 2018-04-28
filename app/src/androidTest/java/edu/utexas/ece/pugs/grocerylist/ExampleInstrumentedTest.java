/*
package edu.utexas.ece.pugs.grocerylist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.utexas.ece.pugs.grocerylist.foodstuff.Pantry;
import edu.utexas.ece.pugs.grocerylist.foodstuff.PantryItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Purchase;
import edu.utexas.ece.pugs.grocerylist.foodstuff.Quantity;
import edu.utexas.ece.pugs.grocerylist.foodstuff.ShoppingListFoodItem;
import edu.utexas.ece.pugs.grocerylist.foodstuff.User;

import static org.junit.Assert.*;

*/
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *//*

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private FirebaseAuth mAuth;

    public Map<String, PantryItem> pantryItemMap;
    ShoppingListFoodItem shoppingListFoodItem1;
    ShoppingListFoodItem shoppingListFoodItem2;
    Purchase newPurchase1;
    Purchase newPurchase2;

    @Before
    public void initialize() {
        mAuth.signInWithEmailAndPassword("nathanbchin@utexas.edu", "password")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Success", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            User.getInstance().setTriplet(user.getUid(), user.getEmail(), user.getDisplayName());
                            User.getInstance().addUserToDatabase();
                            //new FirebaseLoader().execute(new String[]{});

                        } else {
                            // If sign in fails, display a message to the user.
                            */
/*Log.w("Authentication failed.", "signInWithEmail:failure", task.getException());*//*

                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                });
    }
        User.getInstance().setTriplet("taYjUCOjrHUOcQbvoP208UDBZPu2",
                "nathanbchin@utexas.edu", "huawei");
        */
/*new SynchronousFirebaseLoader().execute();*//*

        User.getInstance().getPantryReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, PantryItem> pantryItemMap = (Map<String, PantryItem>) dataSnapshot.getValue();
                if (pantryItemMap == null) {
                    Pantry.getInstance().setPantryItems(new HashMap<String, PantryItem>());
                } else {
                    Pantry.getInstance().setPantryItems(pantryItemMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        shoppingListFoodItem1 = new ShoppingListFoodItem("69", "original", "pineapple",
                new Quantity(3, "oz", "oz", "ounces"), "hard",
                "produce", "pineapple.jpg", new Date());
        shoppingListFoodItem2= new ShoppingListFoodItem("420", "original", "pineapple",
                new Quantity(3, "oz", "oz", "ounces"), "hard",
                "produce", "pineapple.jpg", new Date());

        newPurchase1 = new Purchase(shoppingListFoodItem1);
        newPurchase2 = new Purchase(shoppingListFoodItem2);

        PantryItem pantryItem1 = new PantryItem();
        pantryItem1.addPurchase(newPurchase1);

        PantryItem pantryItem2 = new PantryItem();
        pantryItem1.addPurchase(newPurchase2);

        pantryItemMap = new HashMap<>();
        pantryItemMap.put("69", pantryItem1);
        pantryItemMap.put("420", pantryItem2);
    }

    @Test
    public void useAppContext() throws Exception {
        assertEquals(pantryItemMap, Pantry.getInstance().getPantryItems());
        // Context of the app under test.
        */
/*Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("edu.utexas.ece.pugs.grocerylist", appContext.getPackageName());*//*

    }
}
*/
