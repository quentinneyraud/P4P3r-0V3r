package fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnSignUpEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnSignUpDispatcher implements OnCompleteListener<AuthResult> {

    public OnSignUpDispatcher() {
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        OnSignUpEvent onSignUpEvent = new OnSignUpEvent();

        if (!task.isSuccessful()) {
            onSignUpEvent.setSuccessful(false);
            onSignUpEvent.setErrorMessage(task.getException().getMessage());
        } else {
            onSignUpEvent.setUid(task.getResult().getUser().getUid());
        }

        BusProvider.getInstance().post(onSignUpEvent);
    }
}
