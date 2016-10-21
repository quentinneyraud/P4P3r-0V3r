package fr.quentinneyraud.www.p4p3r0v3r.Conversation.model;

import com.google.firebase.database.Exclude;

import java.util.List;

import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

/**
 * Created by quentin on 11/10/2016.
 */

public class Conversation {

    private String uid;
    private List<Message> messages;
    private List<User> users;

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void pushMessage(Message message) {
        this.messages.add(message);
    }

    @Exclude
    public String getContactPseudo() {
        String contactPseudo = "";
        String currentUserId = AccountService.getInstance()
                .getCurrentUser().getUid();

        for (User user : this.getUsers()) {
            if (!user.getUid().equals(currentUserId)) {
                contactPseudo += " " + user.getPseudo();
            }
        }

        return contactPseudo;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "uid='" + uid + '\'' +
                ", messages=" + messages +
                ", users=" + users +
                '}';
    }
}
