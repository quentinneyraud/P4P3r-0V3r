package fr.quentinneyraud.www.p4p3r0v3r;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.OnSingleValueEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.User;
import fr.quentinneyraud.www.p4p3r0v3r.utils.Firebase;

public class SplashScreenActivity extends AppCompatActivity {

    static final String TAG = "=== SplashScreen ===";
    static final String CHECK_USER_LOGGED = "CHECK_USER_LOGGED";

    Firebase firebase;
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        bus = BusProvider.getInstance();
        bus.register(this);

        firebase = Firebase.getInstance();
        // Try to get version to test user connection
        firebase.getSingleValueByReference("version", CHECK_USER_LOGGED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Subscribe
    public void onCheckUserLogged(OnSingleValueEvent event) {
        if (event.getAction().equals(CHECK_USER_LOGGED)) {
            switch (event.getState()) {
                case "SUCCESS":
                    this.onCheckUserLoggedSuccess();
                    break;
                case "ERROR":
                    this.onCheckUserLoggedError();
                    break;
                default:
                    break;
            }
        }
    }

    public void onCheckUserLoggedSuccess () {
        String userLoggedUid = firebase.getLoggedUser().getUid();
        User user = new User(userLoggedUid);
        // user.getProfileFromFirebase();
    }

    public void onCheckUserLoggedError () {
        Intent i = new Intent(SplashScreenActivity.this, SignInActivity.class);
        startActivity(i);
        finish();
    }

}
