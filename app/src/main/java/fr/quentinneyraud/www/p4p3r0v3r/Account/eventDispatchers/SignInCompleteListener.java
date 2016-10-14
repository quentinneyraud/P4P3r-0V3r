package fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignInErrorEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignInSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class SignInCompleteListener implements OnCompleteListener<AuthResult> {

    public SignInCompleteListener() {
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if (!task.isSuccessful()) {
            BusProvider.getInstance()
                    .post(new SignInErrorEvent(task.getException().getLocalizedMessage()));
        } else {
            BusProvider.getInstance()
                    .post(new SignInSuccessEvent());
        }
    }
}
