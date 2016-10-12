package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import android.support.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import fr.quentinneyraud.www.p4p3r0v3r.Account.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Events.AddUserEvent;
import fr.quentinneyraud.www.p4p3r0v3r.Events.BusProvider;
import fr.quentinneyraud.www.p4p3r0v3r.User.User;

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

    public void getConversations() {
        String uid = AccountService.getInstance().getUser().getUid();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users").child(uid);

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Conversation conversation = dataSnapshot.getValue(Conversation.class);

                conversations.add(conversation);
                AddUserEvent addUserEvent = new AddUserEvent(user);
                BusProvider.getInstance().post(addUserEvent);
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
    }
}
