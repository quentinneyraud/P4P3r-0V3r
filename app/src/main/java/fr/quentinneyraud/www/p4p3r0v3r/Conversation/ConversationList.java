package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import java.util.HashMap;
import java.util.List;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.utils.BusProvider;

/**
 * Created by quentin on 21/10/2016.
 */

public class ConversationList {

    private static final String TAG = "ConversationList";

    private static HashMap<String, Conversation> list = new HashMap<>();
    private static ConversationList instance;

    private ConversationList() {
        BusProvider.getInstance().register(this);
    }

    public static ConversationList getInstance() {
        if (instance == null) {
            instance = new ConversationList();
        }

        return instance;
    }

    public void addConversation(Conversation conversation) {
        list.put(conversation.getUid(), conversation);
    }

    public void addMessage(String conversationUid, Message message) {
        list.get(conversationUid).pushMessage(message);
    }

    public HashMap<String, Message> getMessageByConversationUid(String conversationUid) {
        return list.get(conversationUid).getMessages();
    }

    public Conversation getConversationByUid(String conversationUid) {
        return list.get(conversationUid);
    }
}
