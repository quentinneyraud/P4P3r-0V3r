package fr.quentinneyraud.www.p4p3r0v3r.Conversation.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractSuccessEvent;

/**
 * Created by quentin on 21/10/2016.
 */

public class ConversationCreationSuccess extends AbstractSuccessEvent {

    private String conversationUid;

    public ConversationCreationSuccess() {
    }

    public ConversationCreationSuccess(String conversationUid) {
        this.conversationUid = conversationUid;
    }

    public String getConversationUid() {
        return conversationUid;
    }

    public void setConversationUid(String conversationUid) {
        this.conversationUid = conversationUid;
    }
}
