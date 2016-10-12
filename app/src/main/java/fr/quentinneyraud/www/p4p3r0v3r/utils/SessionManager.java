package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Subscribe;

import java.util.HashMap;
import java.util.Map;

import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.OnAuthStatusChanged;
import fr.quentinneyraud.www.p4p3r0v3r.User.User;

/**
 * Created by quentin on 11/10/2016.
 */

public class SessionManager {

    static final String TAG = "=== SessionManager ===";
    private User user;
    private static SessionManager instance;

    private SessionManager() {
        BusProvider.getInstance().register(this);
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }

        return instance;
    }

    @Subscribe
    public void OnAuthStatusChanged(OnAuthStatusChanged event) {
        if (event.getConnected()) {
            this.onUserConnect(event.getUid());
        } else {
            this.onUserDisconnect();
        }
    }

    // get all datas of user set it in property
    public void onUserConnect(String uid) {
        Log.d(TAG, "user connected = " + uid);

        instance.user = new User(uid);
        instance.user.
    }

    public void onUserDisconnect() {
        Log.d(TAG, "user disconnected");

        instance.user = null;
    }
}
