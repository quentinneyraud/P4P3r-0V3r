package fr.quentinneyraud.www.p4p3r0v3r.Account.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnAuthStateChangedDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnSignInDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnSignUpDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;

/**
 * Created by quentin on 12/10/2016.
 */

public class AccountService {

    static final String TAG = "=== AccountService ===";

    private static AccountService instance;

    private AccountService() {
        BusProvider.getInstance().register(this);
    }

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }

        return instance;
    }

    public void signIn(String email, String password) {
        Log.d(TAG, "sign in");
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnSignInDispatcher());
    }

    public void signUp(String email, String password) {
        Log.d(TAG, "sign up");
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnSignUpDispatcher());
    }

    public void listenAuthentication() {
        Log.d(TAG, "listen auth");
        FirebaseAuth.getInstance()
                .addAuthStateListener(new OnAuthStateChangedDispatcher());
    }
}
