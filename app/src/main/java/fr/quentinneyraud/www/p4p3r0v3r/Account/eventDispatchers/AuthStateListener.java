package fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.UserAuthenticatedEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.UserUnauthenticatedEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class AuthStateListener implements FirebaseAuth.AuthStateListener {

    private Boolean FLAG = true;

    public AuthStateListener() {
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            if (FLAG) {
                BusProvider.getInstance()
                        .post(new UserAuthenticatedEvent(user.getUid()));
                FLAG = false;
            }
        } else {
            BusProvider.getInstance()
                    .post(new UserUnauthenticatedEvent());
            FLAG = true;
        }

    }
}
