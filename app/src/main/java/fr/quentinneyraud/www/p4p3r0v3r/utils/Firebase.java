package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Bus;

import fr.quentinneyraud.www.p4p3r0v3r.Events.AbstractEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.UserEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Events.VersionEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.User;

/**
 * Created by quentin on 11/10/2016.
 */

public class Firebase {

    static final String TAG = "=== Firebase ===";

    private static Firebase instance;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Bus bus;
    private VersionEvent versionEvent;

    private Firebase() {
        bus = BusProvider.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public static Firebase getInstance() {
        if (instance == null) {
            instance = new Firebase();
        }

        return instance;
    }

    public FirebaseUser getLoggedUser () {
        return firebaseAuth.getCurrentUser();
    }

    public void getVersion () {
        versionEvent = new VersionEvent();
        DatabaseReference ref = firebaseDatabase.getReference("version");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                versionEvent.setState("SUCCESS");
                bus.post(versionEvent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                versionEvent.setState("ERROR");
                bus.post(versionEvent);
            }
        });
    }
}
