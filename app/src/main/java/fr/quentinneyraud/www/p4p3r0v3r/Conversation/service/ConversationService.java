package fr.quentinneyraud.www.p4p3r0v3r.Conversation.service;

import java.util.ArrayList;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;

/**
 * Created by quentin on 12/10/2016.
 */

public class ConversationService {

    static final String TAG = "=== ConversationService ===";

    private static ConversationService instance;
    private ArrayList<Conversation> conversations;

    private ConversationService() {
        BusProvider.getInstance().register(this);
    }

    public static ConversationService getInstance() {
        if (instance == null) {
            instance = new ConversationService();
        }

        return instance;
    }

    public void getConversationByUid(String conversationUid) {

    }

    /*public void getConversations() {
        // Create reference to /users/<uid>/conversations
        AccountService.getInstance().getUser().getConversations();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users").child(uid);

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String conversation uid = dataSnapshot.getKey();
                Conversation conversation = dataSnapshot.getValue(Conversation.class);

                conversations.add(conversation);
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
        })
    }*/
}
