package fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fr.quentinneyraud.www.p4p3r0v3r.User.events.SaveUserErrorEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.SaveUserSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class saveUserListener implements DatabaseReference.CompletionListener {

    private User user;

    public saveUserListener() {
    }

    public saveUserListener(User user) {
        this.user = user;
    }

    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

        if (databaseError != null) {

            FirebaseDatabase.getInstance()
                    .getReference("pseudo")
                    .child(this.user.getPseudo())
                    .setValue(this.user.getUid())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                BusProvider.getInstance()
                                        .post(new SaveUserSuccessEvent());
                            } else {
                                BusProvider.getInstance()
                                        .post(new SaveUserErrorEvent(task.getException().getLocalizedMessage()));
                            }
                        }
                    });
        } else {
            try {

                BusProvider.getInstance()
                        .post(new SaveUserErrorEvent(databaseError.getMessage()));
            } catch (Exception e) {

            }
        }
    }
}
