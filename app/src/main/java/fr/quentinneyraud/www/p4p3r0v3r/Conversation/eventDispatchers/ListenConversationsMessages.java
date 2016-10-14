package fr.quentinneyraud.www.p4p3r0v3r.Conversation.eventDispatchers;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.events.MessageAdded;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 14/10/2016.
 */

public class ListenConversationsMessages implements ChildEventListener {

    private String conversationUid;

    public ListenConversationsMessages() {
    }

    public ListenConversationsMessages(String conversationUid) {
        this.conversationUid = conversationUid;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        BusProvider.getInstance()
                .post(new MessageAdded(conversationUid, dataSnapshot.getValue(Message.class)));
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
