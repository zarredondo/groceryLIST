package edu.utexas.ece.pugs.grocerylist;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by zarredondo on 3/9/2018.
 */

public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
