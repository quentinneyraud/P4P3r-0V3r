package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    // Shared preferences config
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "p4p3r0v3rPreferences";

    // Structure config
    private String UID_KEY = "UID_KEY";

    public SharedPreferencesManager(Context context){
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }
}
