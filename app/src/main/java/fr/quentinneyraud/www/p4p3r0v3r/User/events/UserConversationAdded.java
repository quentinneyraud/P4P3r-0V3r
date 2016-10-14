package fr.quentinneyraud.www.p4p3r0v3r.User.events;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractSuccessEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class UserConversationAdded extends AbstractSuccessEvent {

    private Conversation conversation;

    public UserConversationAdded() {
        super();
    }

    public UserConversationAdded(Conversation conversation) {
        this.setConversation(conversation);
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public String toString() {
        return "UserConversationAdded{" +
                "conversation=" + conversation +
                '}';
    }
}
