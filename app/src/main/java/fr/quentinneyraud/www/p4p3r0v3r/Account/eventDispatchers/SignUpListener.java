package fr.quentinneyraud.www.p4p3r0v3r.Account.eventDispatchers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignUpErrorEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Account.events.SignUpSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class SignUpListener implements OnCompleteListener<AuthResult> {

    private String pseudo;

    public SignUpListener() {
    }

    public SignUpListener(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if (!task.isSuccessful()) {
            BusProvider.getInstance()
                    .post(new SignUpErrorEvent(task.getException().getMessage()));
        } else {
            BusProvider.getInstance()
                    .post(new SignUpSuccessEvent(task.getResult().getUser().getUid(), this.getPseudo()));
        }
    }
}
