package fr.quentinneyraud.www.p4p3r0v3r.Search.eventDispatchers;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import fr.quentinneyraud.www.p4p3r0v3r.Search.events.SearchUserErrorEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Search.events.SearchUserSuccessEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

public class searchUserListener implements ValueEventListener {

    static final String TAG = "searchUserListener";

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d("searchUserListener", String.valueOf(dataSnapshot));

        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Log.d(TAG, data.toString());
        }
        BusProvider.getInstance().post(new SearchUserSuccessEvent("success"));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d("searchUserListener", databaseError.toString());

        BusProvider.getInstance().post(new SearchUserErrorEvent(databaseError.getMessage()));
    }
}


