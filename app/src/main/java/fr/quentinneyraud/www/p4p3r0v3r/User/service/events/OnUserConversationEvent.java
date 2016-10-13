package fr.quentinneyraud.www.p4p3r0v3r.User.service.events;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;

/**
 * Created by quentin on 13/10/2016.
 */

public class OnUserConversationEvent {

    private Boolean isSuccessful;
    private String errorMessage;
    private String eventType;
    private Conversation conversation;

    public OnUserConversationEvent() {
        this.setSuccessful(true);
        this.setErrorMessage("");
        this.setEventType("");
        this.setConversation(null);
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}
