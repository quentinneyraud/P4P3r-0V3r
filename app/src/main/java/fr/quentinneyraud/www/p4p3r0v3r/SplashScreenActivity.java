package fr.quentinneyraud.www.p4p3r0v3r;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;

import fr.quentinneyraud.www.p4p3r0v3r.utils.SharedPreferencesManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        static final String TAG = "SPLASH_SCREEN_ACTIVITY";
        SharedPreferencesManager sharedPreferencesManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);

            sharedPreferencesManager = new SharedPreferencesManager(this);

            if(sharedPreferencesManager.isUserLogged()){

                HashMap<String,String> user = sharedPreferencesManager.getUser();

                // Auth with shared preferences datas
                SessionManager.auth(this, user.get("EMAIL"), user.get("PASSWORD"), new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {

                        // Save user
                        SessionManager.setUser(getBaseContext(), authData.getUid());

                        // Start notes activity
                        Intent i = new Intent(SplashScreenActivity.this, NotesActivity.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(SplashScreenActivity.this, "Connexion requise", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                // start account activity
                Intent i = new Intent(SplashScreenActivity.this, AccountActivity.class);
                startActivity(i);
                finish();
            }



        }
    }
}
