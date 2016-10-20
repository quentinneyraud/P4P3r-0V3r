package fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers;

import android.util.Log;

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

public class getUserListener implements ValueEventListener {

    static final String TAG = "getUserListener";

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for (DataSnapshot test : dataSnapshot.getChildren()) {
            Log.d(TAG, test.toString());
        }
        Log.d("getUserListener", String.valueOf(dataSnapshot));
//        BusProvider.getInstance()
  //              .post(new GetUserSuccessEvent(dataSnapshot.getValue(User.class)));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        BusProvider.getInstance()
                .post(new GetUserCancelledEvent(databaseError.getMessage()));
    }
}
