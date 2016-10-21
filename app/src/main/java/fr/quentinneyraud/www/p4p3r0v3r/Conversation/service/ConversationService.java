package fr.quentinneyraud.www.p4p3r0v3r.Conversation.service;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.eventDispatchers.ListenConversationsMessages;
import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
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
}
