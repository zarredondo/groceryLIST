package edu.utexas.ece.pugs.grocerylist.foodstuff;

import android.app.Application;

import com.google.firebase.FirebaseApp;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.utexas.ece.pugs.grocerylist.FirebaseLoader;
import edu.utexas.ece.pugs.grocerylist.SynchronousFirebaseLoader;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Created by zarredondo on 4/25/2018.
 */

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({Pantry.class})

public class PantryTest extends Application {
    private DatabaseReference mockedDatabaseReference;

    public Map<String, PantryItem> pantryItemMap;
    ShoppingListFoodItem shoppingListFoodItem1;
    ShoppingListFoodItem shoppingListFoodItem2;
    Purchase newPurchase1;
    Purchase newPurchase2;

    @Before
    public void initialize() {
        User.getInstance().getFirebaseReference().goOffline();

        User.getInstance().setTriplet("taYjUCOjrHUOcQbvoP208UDBZPu2",
                "nathanbchin@utexas.edu", "huawei");
        new SynchronousFirebaseLoader().execute();
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
    public void getPantryItems() throws Exception {
        assertEquals(pantryItemMap, Pantry.getInstance().getPantryItems());
    }

    /*@Test
    public void getInstance() throws Exception {

    }

    @Test
    public void setPantryItems() throws Exception {
    }

    @Test
    public void addPurchase() throws Exception {
    }*/

}

/*
mockedDatabaseReference = Mockito.mock(DatabaseReference.class);

FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);

PowerMockito.mockStatic(FirebaseDatabase.class);
when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);
*/

