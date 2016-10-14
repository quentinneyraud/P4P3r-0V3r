package fr.quentinneyraud.www.p4p3r0v3r.Account.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.AuthStateListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.SignInCompleteListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.SignUpCompleteListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignUpSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.UserAuthenticatedEvent;
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
    private User currentUser;

    private AccountService() {
        BusProvider.getInstance().register(this);
    }

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }

        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void saveCurrentUser() {
        Log.d(TAG, "saveCurrentUser");
        UserService.getInstance()
                .setUserData(this.getCurrentUser());
    }

    public void listenCurrentUserConversations() {
        Log.d(TAG, "listenCurrentUserConversations");
        UserService.getInstance()
                .listenUserConversation(this.getCurrentUser().getUid());
    }

    public void signIn(String email, String password) {
        Log.d(TAG, "signIn with credentials : " + email + " " + password);
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new SignInCompleteListener());
    }

    public void signUp(String email, String password, String pseudo) {
        Log.d(TAG, "signUp with credentials : " + email + " " + password);
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new SignUpCompleteListener(pseudo));
    }

    public void listenAuthState() {
        Log.d(TAG, "listenAuthState");
        FirebaseAuth.getInstance()
                .addAuthStateListener(new AuthStateListener());
    }

    @Subscribe
    public void userAuthenticatedEvent(UserAuthenticatedEvent userAuthenticatedEvent) {
        Log.d(TAG, "receive UserAuthenticatedEvent : " + userAuthenticatedEvent.toString());
        UserService.getInstance()
            .listenCurrentUserDataChange(userAuthenticatedEvent.getUid());
    }

    @Subscribe
    public void onCurrentUserDataChange(OnCurrentUserDataChange onCurrentUserDataChange) {
        Log.d(TAG, "receive onCurrentUserDataChange : " + onCurrentUserDataChange.toString());
        if (onCurrentUserDataChange.getSuccessful()) {
            AccountService.getInstance()
                .setCurrentUser(onCurrentUserDataChange.getUser());
            AccountService.getInstance()
                    .listenCurrentUserConversations();
        }
    }

    @Subscribe
    public void signUpSuccessEvent(SignUpSuccessEvent signUpSuccessEvent) {
        Log.d(TAG, "receive SignUpSuccessEvent : " + signUpSuccessEvent);

        User user = new User(signUpSuccessEvent.getUid());
        user.setPseudo(signUpSuccessEvent.getPseudo());

        AccountService.getInstance()
                .setCurrentUser(user);

        AccountService.getInstance()
                .saveCurrentUser();
    }
}
