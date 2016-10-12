package fr.quentinneyraud.www.p4p3r0v3r;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity implements LoginFragment.OnLoginListener, RegisterFragment.OnRegisterListener {

    static final String TAG = "AccountActivity";

    private FirebaseAuth fireAuth;
    private FirebaseAuth.AuthStateListener fireAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        if (savedInstanceState == null) {
            LoginFragment loginFragment = new LoginFragment();
            changeFragment(loginFragment);
        }

    //    fireAuth = FirebaseAuth.getInstance();

        //Listen changes on authentication state
      /**  fireAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    // TODO : Redirect to conversations page and set in storage the user id
                    // Intent intent = new Intent(MainActivity.this, ConversationsActivity.class);
                    // startActivity(intent);

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    //TODO : Redirect to login page
                }
            }
        }; **/
    }

    @Override
    public void onStart() {
        super.onStart();
      //  fireAuth.addAuthStateListener(fireAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (fireAuthListener != null) {
            fireAuth.removeAuthStateListener(fireAuthListener);
        }
    }

    @Override
    public void onSignInClick(String email, String password) {
        fireAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "on complete: " + task.isSuccessful());
                    }
                });
    }

    @Override
    public void onNoAccountClick() {
        RegisterFragment registerFragment = new RegisterFragment();
        changeFragment(registerFragment);
    }


    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_account, fragment)
                .commit();
    }

    @Override
    public void onRegisterClick() {
        //TODO : Intent to go to Conversation Activity and set in storage
    }
}
