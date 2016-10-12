package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.OnAuthStatusChanged;
import fr.quentinneyraud.www.p4p3r0v3r.User.User;

/**
 * Created by quentin on 11/10/2016.
 */

public class SessionManager {

    public String test;
    private User user;
    private static SessionManager instance;
    static final String TAG = "=== SessionManager ===";

    private SessionManager() {
        BusProvider.getInstance().register(this);
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }

        return instance;
    }

    public void setAuthListener() {
    }

    @Subscribe
    public void OnAuthStatusChanged(OnAuthStatusChanged event) {
        if (event.getConnected()) {
            this.onUserConnect(event.getUid());
        } else {
            this.onUserDisconnect();
        }
    }

    public void onUserConnect(String uid) {
        Log.d(TAG, "user connected = " + uid);
        SessionManager sessionManager = SessionManager.getInstance();

        Firebase.getInstance().getUser(uid, new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onUserDisconnect() {
        Log.d(TAG, "user disconnected");
    }

    public void setUser(User user) {
        this.user = user;
    }
}
