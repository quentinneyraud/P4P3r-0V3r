package fr.quentinneyraud.www.p4p3r0v3r.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignInErrorEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignUpErrorEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.UserAuthenticatedEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.fragments.SignInFragment;
import fr.quentinneyraud.www.p4p3r0v3r.Account.fragments.SignUpFragment;
import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.service.ConversationService;
import fr.quentinneyraud.www.p4p3r0v3r.MainActivity;
import fr.quentinneyraud.www.p4p3r0v3r.R;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

public class AccountActivity extends AppCompatActivity implements SignInFragment.SignInFragmentListener, SignUpFragment.SignUpFragmentListener {

    private SignInFragment signInFragment;
    private SignUpFragment signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BusProvider.getInstance().register(this);

        signInFragment = new SignInFragment();
        signUpFragment = new SignUpFragment();

        if (savedInstanceState == null) {
            changeFragment(signInFragment);
        }

        User test = new User();
        test.setUid("W2hqtWs8XGgk84f9djJJVNKUwba2");
        test.setPseudo("quentin2");

        User testz = new User();
        testz.setUid("W2hqtWs8XGgk84f9djJJVNKUwba3");
        testz.setPseudo("quentin1");

        ConversationService.getInstance()
                .addConversation(test, testz);
    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_account__fragment_container, fragment)
                .commit();
    }

    @Override
    public void onSignInButtonClick(String email, String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            AccountService.getInstance()
                    .signIn(email, password);
        }
    }

    @Subscribe
    public void signInErrorEvent(SignInErrorEvent signInErrorEvent) {
        Toast.makeText(getBaseContext(), signInErrorEvent.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNoAccountTextViewClick() {
        changeFragment(signUpFragment);
    }

    @Override
    public void onSignUpButtonClick(String email, String password, String pseudo) {
        if (!email.isEmpty() && !password.isEmpty() && !pseudo.isEmpty()) {
            AccountService.getInstance()
                    .signUp(email, password, pseudo);
        }
    }

    @Subscribe
    public void signUpErrorEvent(SignUpErrorEvent signUpErrorEvent) {
        Toast.makeText(getBaseContext(), signUpErrorEvent.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    @Subscribe
    public void userAuthenticatedEvent(UserAuthenticatedEvent userAuthenticatedEvent) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
