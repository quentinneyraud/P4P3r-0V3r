package fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.LogoutErrorEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.LogoutSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

public class LogoutListener implements OnCompleteListener<AuthResult> {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (!task.isSuccessful()) {
            BusProvider.getInstance()
                    .post(new LogoutErrorEvent(task.getException().getLocalizedMessage()));
        } else {
            BusProvider.getInstance()
                    .post(new LogoutSuccessEvent());
        }
    }
}
