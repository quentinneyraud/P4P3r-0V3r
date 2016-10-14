package fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.OnUserConversationsEvent;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class ListenUserConversationsChildListener implements ChildEventListener {

    private OnUserConversationsEvent onUserConversationEvent;

    public ListenUserConversationsChildListener() {
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        onUserConversationEvent = new OnUserConversationsEvent();
        onUserConversationEvent.setEventType("ADD");

        FirebaseDatabase.getInstance()
                .getReference("conversations")
                .child(dataSnapshot.getValue().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        onUserConversationEvent.setConversation(dataSnapshot.getValue(Conversation.class));
                        BusProvider.getInstance()
                                .post(onUserConversationEvent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        onUserConversationEvent.setSuccessful(false);
                        BusProvider.getInstance()
                                .post(onUserConversationEvent);
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
