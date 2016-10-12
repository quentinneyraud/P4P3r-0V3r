package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import java.util.ArrayList;

/**
 * Created by quentin on 12/10/2016.
 */

public class ConversationList {
    private ArrayList<Conversation> conversations;

    public ConversationList() {
    }

    public void AddConversation(Conversation conversation){
        conversations.add(conversation);
    }

}
