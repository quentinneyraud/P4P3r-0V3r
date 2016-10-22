package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by quentin on 14/10/2016.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        MyApplication.context = getApplicationContext();
    }

    public static Context getMyApplicationContext() {
        return MyApplication.context;
    }
}
