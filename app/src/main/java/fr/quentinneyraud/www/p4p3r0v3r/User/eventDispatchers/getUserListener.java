package fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fr.quentinneyraud.www.p4p3r0v3r.User.events.GetUserCancelledEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.GetUserSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class GetUserListener implements ValueEventListener {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        BusProvider.getInstance()
                .post(new GetUserSuccessEvent(dataSnapshot.getValue(User.class)));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        BusProvider.getInstance()
                .post(new GetUserCancelledEvent(databaseError.getMessage()));
    }
}
