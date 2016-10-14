package fr.quentinneyraud.www.p4p3r0v3r.Conversation.events;

import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractSuccessEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class MessageAdded extends AbstractSuccessEvent {

    private String conversationUid;
    private Message message;

    public MessageAdded() {
    }

    public MessageAdded(String conversationUid, Message message) {
        this.conversationUid = conversationUid;
    }

    public String getConversationUid() {
        return conversationUid;
    }

    public void setConversationUid(String conversationUid) {
        this.conversationUid = conversationUid;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
