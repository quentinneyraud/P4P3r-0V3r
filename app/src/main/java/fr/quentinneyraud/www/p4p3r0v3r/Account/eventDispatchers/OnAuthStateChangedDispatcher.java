package fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnAuthStateChanged;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnAuthStateChangedDispatcher implements FirebaseAuth.AuthStateListener {

    public OnAuthStateChangedDispatcher() {
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        OnAuthStateChanged onAuthStateChanged;

        if (user != null) {
            onAuthStateChanged = new OnAuthStateChanged(true, user.getUid());
        } else {
            onAuthStateChanged = new OnAuthStateChanged(false, null);
        }
        BusProvider.getInstance().post(onAuthStateChanged);
    }
}