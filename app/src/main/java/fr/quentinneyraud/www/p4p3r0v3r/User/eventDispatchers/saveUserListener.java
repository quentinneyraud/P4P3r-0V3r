package fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import fr.quentinneyraud.www.p4p3r0v3r.User.events.SaveUserErrorEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.SaveUserSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class saveUserListener implements DatabaseReference.CompletionListener {
    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

        if (databaseError != null) {
            BusProvider.getInstance()
                    .post(new SaveUserErrorEvent(databaseError.getMessage()));
        } else {
            BusProvider.getInstance()
                    .post(new SaveUserSuccessEvent());
        }
    }
}
