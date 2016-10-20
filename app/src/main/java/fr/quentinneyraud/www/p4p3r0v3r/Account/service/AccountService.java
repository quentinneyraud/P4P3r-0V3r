package fr.quentinneyraud.www.p4p3r0v3r.Account.service;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.AuthStateListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.SignInListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.SignUpListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignUpSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.UserAuthenticatedEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.service.ConversationService;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.GetUserSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.UserConversationAdded;
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
                .saveUser(this.getCurrentUser());
    }

    public void getCurrentUserData(String uid) {
        UserService.getInstance()
                .getUser(uid);
    }

    public void listenCurrentUserConversations() {
        Log.d(TAG, "listenCurrentUserConversations");
        UserService.getInstance()
                .listenUserConversations(this.getCurrentUser().getUid());
    }

    public void signIn(String email, String password) {
        Log.d(TAG, "signIn with credentials : " + email + " " + password);
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new SignInListener());
    }

    public void signUp(String email, String password, String pseudo) {
        Log.d(TAG, "signUp with credentials : " + email + " " + password);
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new SignUpListener(pseudo));
    }

    public void listenAuthState() {
        Log.d(TAG, "listenAuthState");
        FirebaseAuth.getInstance()
                .addAuthStateListener(new AuthStateListener());
    }

    @Subscribe
    public void userAuthenticatedEvent(UserAuthenticatedEvent userAuthenticatedEvent) {
        Log.d(TAG, "receive UserAuthenticatedEvent : " + userAuthenticatedEvent.toString());
        AccountService.getInstance()
                .getCurrentUserData(userAuthenticatedEvent.getUid());
    }

    @Subscribe
    public void getUserSuccessEvent(GetUserSuccessEvent getUserSuccessEvent) {
        Log.d(TAG, "receive GetUserSuccessEvent : " + getUserSuccessEvent.toString());
        AccountService.getInstance()
                .setCurrentUser(getUserSuccessEvent.getUser());
        AccountService.getInstance()
                .listenCurrentUserConversations();
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

    @Subscribe
    public void userConversationAdded(UserConversationAdded userConversationAdded) {
        Log.d(TAG, "receive UserConversationAdded event : " + userConversationAdded.toString());

        ConversationService.getInstance()
                .listenConversationMessages(userConversationAdded.getConversation().getUid());
    }
}
