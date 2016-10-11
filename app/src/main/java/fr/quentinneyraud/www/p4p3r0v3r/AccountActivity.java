package fr.quentinneyraud.www.p4p3r0v3r;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    static final String TAG = "AccountActivity";

    private FirebaseAuth fireAuth;
    private FirebaseAuth.AuthStateListener fireAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        fireAuth = FirebaseAuth.getInstance();

        //Listen changes on authentication state
        fireAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getEmail());
                    // TODO : Redirect to conversations page
                    // Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                    // startActivity(intent);

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        fireAuth.addAuthStateListener(fireAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (fireAuthListener != null) {
            fireAuth.removeAuthStateListener(fireAuthListener);
        }
    }

}
