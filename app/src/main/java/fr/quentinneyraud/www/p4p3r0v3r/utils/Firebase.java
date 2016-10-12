package fr.quentinneyraud.www.p4p3r0v3r.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.otto.Bus;

import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.OnAuthStatusChanged;

/**
 * Created by quentin on 11/10/2016.
 */

public class Firebase implements FirebaseAuth.AuthStateListener {

    static final String TAG = "=== Firebase ===";

    private static Firebase instance;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Bus bus;
    private OnAuthStatusChanged onAuthStatusChanged;

    private Firebase() {
        bus = BusProvider.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public static Firebase getInstance() {
        if (instance == null) {
            instance = new Firebase();
        }
        Log.d(TAG, "get firebase instance !!!!");
        return instance;
    }

    public void createAccount(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }

    public void logUser(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener);
    }

    public void addAuthStateListener() {
        firebaseAuth.addAuthStateListener(this);
    }

    public void getUser(String uid, ValueEventListener vel) {
        DatabaseReference dbRef = firebaseDatabase.getReference("users/" + uid)
                .addListenerForSingleValueEvent(vel);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        Log.d(TAG, "Auth state changed, dispatching event");

        onAuthStatusChanged = new OnAuthStatusChanged();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            onAuthStatusChanged.setConnected(true);
            onAuthStatusChanged.setUid(user.getUid());
        } else {
            onAuthStatusChanged.setConnected(false);
        }
        bus.post(onAuthStatusChanged);
    }
}
