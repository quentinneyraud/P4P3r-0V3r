package fr.quentinneyraud.www.p4p3r0v3r.Conversation.model;

import java.util.List;

import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

/**
 * Created by quentin on 11/10/2016.
 */

public class Conversation {

    private String uid;
    private List<Message> messages;
    private List<String> usersUid;

    public Conversation() {
    }

    public Conversation(String uid) {
        this.uid = uid;
    }

    public Conversation(String uid, List<Message> messages) {
        this.uid = uid;
        this.messages = messages;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<String> getUsersUid() {
        return usersUid;
    }

    public void setUsersUid(List<String> usersUid) {
        this.usersUid = usersUid;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "uid='" + uid + '\'' +
                ", messages=" + messages +
                ", usersUid=" + usersUid +
                '}';
    }
}
