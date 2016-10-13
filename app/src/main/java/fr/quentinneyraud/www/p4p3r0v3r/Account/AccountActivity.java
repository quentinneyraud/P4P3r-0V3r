package fr.quentinneyraud.www.p4p3r0v3r.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnAuthStateChanged;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnSignInEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnSignUpEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.fragments.SignInFragment;
import fr.quentinneyraud.www.p4p3r0v3r.Account.fragments.SignUpFragment;
import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.MainActivity;
import fr.quentinneyraud.www.p4p3r0v3r.R;

public class AccountActivity extends AppCompatActivity implements SignInFragment.SignInFragmentListener, SignUpFragment.SignUpFragmentListener {

    static final String TAG = "=== AccountActivity ===";
    private SignInFragment signInFragment;
    private SignUpFragment signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        signInFragment = new SignInFragment();
        signUpFragment = new SignUpFragment();

        if (savedInstanceState == null) {
            changeFragment(signInFragment);
        } else {
            Log.d(TAG, "saved instance is not null");
        }
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
    public void onSignInEvent(OnSignInEvent onSignInEvent) {
        if(!onSignInEvent.getSuccessful()) {
            Toast.makeText(getBaseContext(), onSignInEvent.getUiMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNoAccountTextViewClick() {
        changeFragment(signUpFragment);
    }

    @Override
    public void onSignUpButtonClick(String email, String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            AccountService.getInstance()
                    .signUp(email, password);
        }
    }

    @Subscribe
    public void onSignUpEvent(OnSignUpEvent onSignUpEvent) {
        if(!onSignUpEvent.getSuccessful()) {
            Toast.makeText(getBaseContext(), onSignUpEvent.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe
    public void onAuthStateChanged(OnAuthStateChanged event) {
        if (event.getConnected()) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}