package fr.quentinneyraud.www.p4p3r0v3r.Conversation.service;

import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.eventDispatchers.ConversationCreationListener;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.eventDispatchers.ListenConversationsMessages;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 12/10/2016.
 */

public class ConversationService {

    private static final String TAG = "ConversationService";
    private static final String REFERENCE = "conversations";

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

    public void listenConversationMessages(String conversationUid) {
        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(conversationUid)
                .child("messages")
                .addChildEventListener(new ListenConversationsMessages(conversationUid));
    }

    public void addConversation(User user, User otherUser) {

        HashMap<String, User> users = new HashMap<>();
        users.put(user.getUid(), user);
        users.put(otherUser.getUid(), otherUser);

        String newConversationUid = FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .push()
                .getKey();

        Conversation conversation = new Conversation();
        conversation.setUid(newConversationUid);
        conversation.setUsers(users);

        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(newConversationUid)
                .setValue(conversation)
                .addOnCompleteListener(new ConversationCreationListener(conversation));
    }

    public void pushMessage(String conversationUid, String text) {

        // get user
        User user = AccountService.getInstance()
                .getCurrentUser();

        // get timestamp
        Calendar calendar = Calendar.getInstance();

        Message message = new Message(text, user.getUid(), String.valueOf(calendar.getTimeInMillis()));

        FirebaseDatabase.getInstance()
                .getReference(REFERENCE)
                .child(conversationUid)
                .child("messages")
                .push()
                .setValue(message);
    }
}
