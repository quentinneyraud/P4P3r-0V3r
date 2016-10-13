package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import java.util.ArrayList;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;

public class ConversationList {
    private ArrayList<Conversation> conversations;

    public ConversationList() {
    }

    public void AddConversation(Conversation conversation){
        conversations.add(conversation);
    }

}
