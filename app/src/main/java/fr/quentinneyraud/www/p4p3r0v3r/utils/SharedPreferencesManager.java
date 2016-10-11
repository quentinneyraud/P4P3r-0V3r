package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPreferencesManager {

    private SharedPreferences.Editor editor;

    // Shared preferences config
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "p4p3r0v3rPreferences";

    // Structure config
    private String UID_KEY = "UID_KEY";

    public SharedPreferencesManager(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public HashMap<String, String> getUser(){
        HashMap<String, String> user = new HashMap<String, String>();

        return user;
    }

    public void LogInUser(String email, String password){

        editor.apply();
    }

    public void LogoutUser(){

        editor.apply();
    }

    public boolean isUserLogged(){
        return false;
    }
}
