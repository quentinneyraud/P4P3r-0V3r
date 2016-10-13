package fr.quentinneyraud.www.p4p3r0v3r.Account.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnAuthStateChangedDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnSignInDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnSignUpDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnAuthStateChanged;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnSignUpEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.UserService;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.events.OnCurrentUserDataChange;

/**
 * Created by quentin on 12/10/2016.
 */

public class AccountService {

    static final String TAG = "=== AccountService ===";
    private static AccountService instance;
    private User user;

    private AccountService() {
        BusProvider.getInstance().register(this);
    }

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }

        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void saveCurrentUser() {
        UserService.getInstance()
                .setUserData(this.getUser());
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

    @Subscribe
    public void onAuthStateChanged(OnAuthStateChanged event) {
        if (event.getConnected()) {
            UserService.getInstance()
                .listenCurrentUserDataChange(event.getUid());
        }
    }

    @Subscribe
    public void onCurrentUserDataChange(OnCurrentUserDataChange onCurrentUserDataChange) {
        if (onCurrentUserDataChange.getSuccessful()) {
            //Log.d(TAG, "current user updated " + onCurrentUserDataChange.getUser().toString());
            AccountService.getInstance()
                .setUser(onCurrentUserDataChange.getUser());
        } else {
            Log.d(TAG, "onUserDataChange : " + onCurrentUserDataChange.getErrorMessage());
        }
    }

    @Subscribe
    public void onSignUpEvent(OnSignUpEvent onSignUpEvent) {
        if(onSignUpEvent.getSuccessful()) {
            User user = new User(onSignUpEvent.getUid());
            user.setPseudo("quentin");
            AccountService.getInstance()
                    .setUser(user);
            AccountService.getInstance()
                    .saveCurrentUser();
        }
    }
}
