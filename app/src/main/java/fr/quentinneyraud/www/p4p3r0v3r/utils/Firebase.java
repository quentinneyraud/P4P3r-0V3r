package fr.quentinneyraud.www.p4p3r0v3r.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Bus;

import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.OnSingleValueEvent;

/**
 * Created by quentin on 11/10/2016.
 */

public class Firebase {

    static final String TAG = "=== Firebase ===";

    private static Firebase instance;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Bus bus;
    private OnSingleValueEvent onSingleValueEvent;

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

    public void getSingleValueByReference (String reference, String action) {
        onSingleValueEvent = new OnSingleValueEvent();
        onSingleValueEvent.setAction(action);
        DatabaseReference ref = firebaseDatabase.getReference(reference);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                onSingleValueEvent.setState("SUCCESS");
                onSingleValueEvent.setDataSnapshot(dataSnapshot);
                bus.post(onSingleValueEvent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                onSingleValueEvent.setState("ERROR");
                bus.post(onSingleValueEvent);
            }
        });
    }
}
