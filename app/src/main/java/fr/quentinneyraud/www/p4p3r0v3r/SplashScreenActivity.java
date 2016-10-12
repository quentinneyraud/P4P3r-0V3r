package fr.quentinneyraud.www.p4p3r0v3r;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.Events.OnAuthStatusChanged;
import fr.quentinneyraud.www.p4p3r0v3r.User.SignInActivity;

public class SplashScreenActivity extends AppCompatActivity {

    static final String TAG = "=== SplashScreen ===";
    private static final int SPLASH_SCREEN_DISPLAY_MS = 3000;

    Bus bus;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        BusProvider.getInstance().register(this);

        AccountService.getInstance().setAuthListener();

        handler = new android.os.Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, SignInActivity.class);
                startActivity(i);
                finish();
            }
        };
        handler.postDelayed(runnable, SPLASH_SCREEN_DISPLAY_MS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Subscribe
    public void OnAuthStatusChanged(OnAuthStatusChanged event) {
        if (event.getConnected()) {
            handler.removeCallbacks(runnable);
            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

}
