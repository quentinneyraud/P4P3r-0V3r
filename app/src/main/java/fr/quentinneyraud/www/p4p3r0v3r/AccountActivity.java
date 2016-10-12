package fr.quentinneyraud.www.p4p3r0v3r;

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

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversationListActivity;
import fr.quentinneyraud.www.p4p3r0v3r.utils.Firebase;

public class AccountActivity extends AppCompatActivity implements LoginFragment.OnLoginListener, RegisterFragment.OnRegisterListener {

    static final String TAG = "AccountActivity";

//    private FirebaseAuth fireAuth;
    //   private FirebaseAuth.AuthStateListener fireAuthListener;

    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        firebase = Firebase.getInstance();

        if (savedInstanceState == null) {
            Log.d(TAG, "changing fragment");
            LoginFragment loginFragment = new LoginFragment();
            changeFragment(loginFragment);
        } else {
            Log.d(TAG, "saved instance is not null");
        }
    }


    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_account, fragment)
                .commit();
    }

    @Override
    public void onSignInClick(String email, String password) {

        if (!email.equals("") && !password.equals("")) {
            firebase.logUser(email, password, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(getBaseContext(), ConversationListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Log.d(TAG, "Empty fields");
            Toast.makeText(this, "Fields must not be empty", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNoAccountClick() {
        RegisterFragment registerFragment = new RegisterFragment();
        changeFragment(registerFragment);
    }


    @Override
    public void onRegisterClick(String email, String password, String passwordRepeat) {

        if (!email.equals("") && !password.equals("") && !passwordRepeat.equals("")) {
            if (password.equals(passwordRepeat)) {
                firebase.createAccount(email, password, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getBaseContext(), ConversationListActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getBaseContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Empty fields");
            Toast.makeText(this, "Fields must not be empty", Toast.LENGTH_LONG).show();

        }
    }

}
