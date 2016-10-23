package fr.quentinneyraud.www.p4p3r0v3r.Account.service;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.AuthStateListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.SignInListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers.SignUpListener;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignUpSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.UserAuthenticatedEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.eventDispatchers.ConversationCreationListener;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.events.ConversationCreationSuccess;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.service.ConversationService;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.GetUserSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.UserConversationAdded;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.UserService;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.utils.SharedPreferencesManager;

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
        UserService.getInstance()
                .saveUser(this.getCurrentUser());
    }

    public void getCurrentUserData(String uid) {
        UserService.getInstance()
                .getUser(uid);
    }

    public void listenCurrentUserConversations() {
        UserService.getInstance()
                .listenUserConversations(this.getCurrentUser().getUid());
    }

    public void signIn(String email, String password) {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new SignInListener());
    }

    public void signUp(String email, String password, String pseudo) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new SignUpListener(pseudo));
    }

    public void listenAuthState() {
        FirebaseAuth.getInstance()
                .addAuthStateListener(new AuthStateListener());
    }

    @Subscribe
    public void userAuthenticatedEvent(UserAuthenticatedEvent userAuthenticatedEvent) {
        AccountService.getInstance()
                .getCurrentUserData(userAuthenticatedEvent.getUid());
    }

    @Subscribe
    public void getUserSuccessEvent(GetUserSuccessEvent getUserSuccessEvent) {
        AccountService.getInstance()
                .setCurrentUser(getUserSuccessEvent.getUser());
        AccountService.getInstance()
                .listenCurrentUserConversations();
    }

    @Subscribe
    public void signUpSuccessEvent(SignUpSuccessEvent signUpSuccessEvent) {
        User user = new User(signUpSuccessEvent.getUid());
        user.setPseudo(signUpSuccessEvent.getPseudo());

        AccountService.getInstance()
                .setCurrentUser(user);

        AccountService.getInstance()
                .saveCurrentUser();
    }

    @Subscribe
    public void userConversationAdded(UserConversationAdded userConversationAdded) {
        ConversationService.getInstance()
                .listenConversationMessages(userConversationAdded.getConversation().getUid());
    }

    @Subscribe
    public void conversationCreationSuccess(ConversationCreationSuccess conversationCreationSuccess) {
        String conversationUid = conversationCreationSuccess.getConversationUid();

        SharedPreferencesManager.getInstance(null).setConversationPassphrase(conversationUid, "test");
    }
}
