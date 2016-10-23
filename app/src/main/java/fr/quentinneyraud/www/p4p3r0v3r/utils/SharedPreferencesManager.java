package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

public class SharedPreferencesManager {

    private static SharedPreferencesManager instance = null;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    // Shared preferences config
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "p4p3r0v3rPreferences";

    // Structure config


    private SharedPreferencesManager(Context context) {
        this.preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = preferences.edit();
    }

    public static SharedPreferencesManager getInstance(@Nullable Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }

        return instance;
    }

    public void setConversationPassphrase(String conversationUid, String passphrase) {
        this.editor.putString(conversationUid, passphrase);
        this.editor.commit();
    }

    public String getConversationPassphrase(String conversationUid) {
        return this.preferences.getString(conversationUid, null);
    }

}
