package fr.quentinneyraud.www.p4p3r0v3r.Account.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnAuthStateChangedDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnSignInDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.OnSignUpDispatcher;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnAuthStateChanged;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnSignUpEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.OnCurrentUserDataChange;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.UserService;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 12/10/2016.
 */

public class AccountService {

    private static AccountService instance;
    private static final String TAG = "AccountService";
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
        Log.d(TAG, "saveCurrentUser");
        UserService.getInstance()
                .setUserData(this.getUser());
    }

    public void signIn(String email, String password) {
        Log.d(TAG, "signIn with credentials : " + email + " " + password);
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnSignInDispatcher());
    }

    public void signUp(String email, String password) {
        Log.d(TAG, "signUp with credentials : " + email + " " + password);
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnSignUpDispatcher());
    }

    public void listenAuthentication() {
        Log.d(TAG, "listenAuthentication");
        FirebaseAuth.getInstance()
                .addAuthStateListener(new OnAuthStateChangedDispatcher());
    }

    @Subscribe
    public void onAuthStateChanged(OnAuthStateChanged event) {
        Log.d(TAG, " receive onAuthStateChangedEvent : " + event.toString());
        if (event.getConnected()) {
            UserService.getInstance()
                .listenCurrentUserDataChange(event.getUid());
        }
    }

    @Subscribe
    public void onCurrentUserDataChange(OnCurrentUserDataChange onCurrentUserDataChange) {
        Log.d(TAG, "receive onCurrentUserDataChange : " + onCurrentUserDataChange.toString());
        if (onCurrentUserDataChange.getSuccessful()) {
            AccountService.getInstance()
                .setUser(onCurrentUserDataChange.getUser());
        }
    }

    @Subscribe
    public void onSignUpEvent(OnSignUpEvent onSignUpEvent) {
        Log.d(TAG, "receive onSignUpEvent : " + onSignUpEvent);
        if(onSignUpEvent.getSuccessful()) {
            User user = new User(onSignUpEvent.getUid());
            user.setPseudo("quentin");
            user.setConversationsUid(new ArrayList<String>());
            AccountService.getInstance()
                    .setUser(user);
            AccountService.getInstance()
                    .saveCurrentUser();
        }
    }
}
