package fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversationList;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.User.events.UserConversationAdded;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 13/10/2016.
 */

public class ListenUserConversationsChildListener implements ChildEventListener {

    public ListenUserConversationsChildListener() {
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        final String conversationUid = dataSnapshot.getValue().toString();

        FirebaseDatabase.getInstance()
                .getReference("conversations")
                .child(conversationUid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Conversation conversation = dataSnapshot.getValue(Conversation.class);
                        conversation.setMessages(new ArrayList<Message>());

                        ConversationList.getInstance()
                                .addConversation(conversation);
                        BusProvider.getInstance()
                                .post(new UserConversationAdded(conversation));
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
