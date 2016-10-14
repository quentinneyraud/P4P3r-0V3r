package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by quentin on 14/10/2016.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
