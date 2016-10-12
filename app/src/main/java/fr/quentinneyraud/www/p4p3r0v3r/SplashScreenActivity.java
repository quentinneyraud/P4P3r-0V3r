package fr.quentinneyraud.www.p4p3r0v3r;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.VersionEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.Firebase;

public class SplashScreenActivity extends AppCompatActivity {

    static final String TAG = "=== SplashScreen ===";

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
        firebase.getVersion();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Subscribe
    public void onUserEvent(VersionEvent event) {
        switch (event.getState()) {
            case "SUCCESS":
                break;
            case "ERROR":
                break;
            default:
                break;
        }
    }

}
