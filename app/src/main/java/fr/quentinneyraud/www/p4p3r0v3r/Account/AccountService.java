package fr.quentinneyraud.www.p4p3r0v3r.Account;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Events.AccountCrudActionEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.OnAuthStatusChanged;
import fr.quentinneyraud.www.p4p3r0v3r.User.User;

/**
 * Created by quentin on 12/10/2016.
 */

public class AccountService implements FirebaseAuth.AuthStateListener {

    static final String TAG = "=== AccountService ===";
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthListener() {
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Subscribe public void onAccountCrudAction(AccountCrudActionEvent accountCrudActionEvent) {
        switch(accountCrudActionEvent.getAction()) {
            case "CREATE":
                this.createAccount(accountCrudActionEvent.getUser());
                break;
            case "READ":
                break;
            case "UPDATE":
                break;
            case "DELETE":
                break;
        }
    }

    public void createAccount (User user) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        OnAuthStatusChanged onAuthStatusChanged;

        if (user != null) {
            onAuthStatusChanged = new OnAuthStatusChanged(true, user.getUid());
            this.user = new User(user.getUid());
        } else {
            onAuthStatusChanged = new OnAuthStatusChanged(false, null);
            this.user = null;
        }
        BusProvider.getInstance().post(onAuthStatusChanged);
    }
}
