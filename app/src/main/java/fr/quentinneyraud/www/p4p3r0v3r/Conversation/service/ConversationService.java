package fr.quentinneyraud.www.p4p3r0v3r.Conversation.service;

import java.util.ArrayList;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 12/10/2016.
 */

public class ConversationService {

    private static final String TAG = "ConversationService";

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
}
