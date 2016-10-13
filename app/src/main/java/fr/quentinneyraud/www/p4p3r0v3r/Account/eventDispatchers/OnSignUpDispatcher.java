package fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers;

import android.support.annotation.NonNull;

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
        OnSignUpEvent signInEvent = new OnSignUpEvent(task.isSuccessful(), task.getException().getMessage());

        BusProvider.getInstance().post(signInEvent);
    }
}
