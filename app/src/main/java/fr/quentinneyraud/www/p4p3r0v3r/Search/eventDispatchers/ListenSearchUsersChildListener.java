package fr.quentinneyraud.www.p4p3r0v3r.Search.eventDispatchers;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListenSearchUsersChildListener implements ChildEventListener {

    public ListenSearchUsersChildListener() {
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

        FirebaseDatabase.getInstance()
                .getReference("users")
                .child(dataSnapshot.getValue().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //BusProvider.getInstance().post(new UserConversationAdded(dataSnapshot.getValue(Conversation.class)));
                        Log.d("ListenSearchUsersCL", dataSnapshot.toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("ListenSearchUsersCL", databaseError.toString());

                    }
                });

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
