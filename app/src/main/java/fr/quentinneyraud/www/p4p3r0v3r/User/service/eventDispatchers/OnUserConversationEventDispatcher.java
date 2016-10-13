package fr.quentinneyraud.www.p4p3r0v3r.User.service.eventDispatchers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.User.service.events.OnUserConversationEvent;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnUserConversationEventDispatcher implements ChildEventListener {

    private OnUserConversationEvent onUserConversationEvent;

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        onUserConversationEvent = new OnUserConversationEvent();
        onUserConversationEvent.setEventType("ADD");

        FirebaseDatabase.getInstance()
                .getReference("conversation")
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
