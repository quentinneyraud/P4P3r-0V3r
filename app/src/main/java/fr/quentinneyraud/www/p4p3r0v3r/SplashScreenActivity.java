package fr.quentinneyraud.www.p4p3r0v3r;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Subscribe;

import fr.quentinneyraud.www.p4p3r0v3r.Account.AccountActivity;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.UserAuthenticatedEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DISPLAY_MS = 3000;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BusProvider.getInstance().register(this);

        AccountService.getInstance().listenAuthState();

        handler = new android.os.Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
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
        try {
            BusProvider.getInstance().unregister(this);
        } catch (Exception e) {

        }
    }

    @Subscribe
    public void userAuthenticatedEvent(UserAuthenticatedEvent userAuthenticatedEvent) {
        handler.removeCallbacks(runnable);
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
