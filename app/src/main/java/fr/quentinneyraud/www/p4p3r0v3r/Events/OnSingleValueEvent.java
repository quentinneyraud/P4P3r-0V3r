package fr.quentinneyraud.www.p4p3r0v3r.Events;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by quentin on 12/10/2016.
 */

public class OnSingleValueEvent extends AbstractEvent {

    public DataSnapshot dataSnapshot;

    public DataSnapshot getDataSnapshot() {
        return dataSnapshot;
    }

    public void setDataSnapshot(DataSnapshot dataSnapshot) {
        this.dataSnapshot = dataSnapshot;
    }
}
