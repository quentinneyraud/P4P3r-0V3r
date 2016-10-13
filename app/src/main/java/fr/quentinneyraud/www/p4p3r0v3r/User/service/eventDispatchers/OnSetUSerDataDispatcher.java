package fr.quentinneyraud.www.p4p3r0v3r.User.service.eventDispatchers;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.events.OnSetUserData;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnSetUSerDataDispatcher implements DatabaseReference.CompletionListener {
    @Override
    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
        OnSetUserData onSetUserData = new OnSetUserData();

        if (databaseError != null) {
            onSetUserData.setSuccessful(false);
            onSetUserData.setErrorMessage(databaseError.getMessage());
        }

        BusProvider.getInstance()
                .post(onSetUserData);
    }
}
