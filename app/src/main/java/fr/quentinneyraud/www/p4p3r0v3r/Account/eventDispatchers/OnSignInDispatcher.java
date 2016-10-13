package fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.OnSignInEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnSignInDispatcher implements OnCompleteListener<AuthResult> {

    public OnSignInDispatcher() {
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Log.d("TAGGGGG", task.getResult().toString());
        OnSignInEvent signInEvent = new OnSignInEvent();

        if (!task.isSuccessful()) {
            signInEvent.setSuccessful(false);
            signInEvent.setErrorMessage(task.getException().getMessage());
        }

        BusProvider.getInstance().post(signInEvent);
    }
}
