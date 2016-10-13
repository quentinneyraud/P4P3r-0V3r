package fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fr.quentinneyraud.www.p4p3r0v3r.User.events.OnCurrentUserDataChange;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnCurrentUserDataChangedDispatcher implements ValueEventListener {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        OnCurrentUserDataChange onUserDataChange = new OnCurrentUserDataChange(dataSnapshot.getValue(User.class));

        BusProvider.getInstance()
                .post(onUserDataChange);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        OnCurrentUserDataChange onUserDataChange = new OnCurrentUserDataChange(databaseError.getMessage());

        BusProvider.getInstance()
                .post(onUserDataChange);
    }
}
