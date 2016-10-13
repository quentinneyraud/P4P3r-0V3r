package fr.quentinneyraud.www.p4p3r0v3r;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnAuthStateChanged;
import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Account.AccountActivity;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;

public class SplashScreenActivity extends AppCompatActivity {

    static final String TAG = "=== SplashScreen ===";
    private static final int SPLASH_SCREEN_DISPLAY_MS = 3000;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        BusProvider.getInstance().register(this);

        AccountService.getInstance().listenAuthentication();

        handler = new android.os.Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                Log.d(TAG, "go to account activity");
                Intent i = new Intent(SplashScreenActivity.this, AccountActivity.class);
                startActivity(i);
                finish();
            }
        };
        handler.postDelayed(runnable, SPLASH_SCREEN_DISPLAY_MS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onAuthStateChanged(OnAuthStateChanged event) {
        if (event.getConnected()) {
            Log.d(TAG, "Auth state changed, start MainActivity");
            handler.removeCallbacks(runnable);
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
