package fr.quentinneyraud.www.p4p3r0v3r.User.eventDispatchers;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

<<<<<<< HEAD
import java.util.HashMap;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.ConversationList;
=======
>>>>>>> master
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
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

        FirebaseDatabase.getInstance()
                .getReference("conversations")
                .child(dataSnapshot.getValue().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
<<<<<<< HEAD
                        Conversation conversation = dataSnapshot.getValue(Conversation.class);
                        Log.d("TAAAG", conversation.toString());
                        // get messages with child listener, they are not ordered here =(
                        conversation.setMessages(new HashMap<String, Message>());

                        ConversationList.getInstance()
                                .addConversation(conversation);
=======
>>>>>>> master
                        BusProvider.getInstance()
                                .post(new UserConversationAdded(dataSnapshot.getValue(Conversation.class)));
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
