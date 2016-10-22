package fr.quentinneyraud.www.p4p3r0v3r.Conversation.model;

import android.util.Log;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;

import fr.quentinneyraud.www.p4p3r0v3r.Account.service.AccountService;
import fr.quentinneyraud.www.p4p3r0v3r.Message.model.Message;
import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

/**
 * Created by quentin on 11/10/2016.
 */

public class Conversation {

    private String uid;
    private HashMap<String, Message> messages;
    private HashMap<String, User> users;

    public Conversation() {
        this.setMessages(new HashMap<String, Message>());
    }

    public Conversation(String uid) {
        this.uid = uid;
    }

    public Conversation(String uid, HashMap<String, Message> messages) {
        this.uid = uid;
        this.messages = messages;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public HashMap<String, Message> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, Message> messages) {
        this.messages = messages;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public void pushMessage(Message message) {
        Log.d("COnversation", "push message, current messages length : " + this.messages.size());
        Log.d("COnversation", "push message : " + message.toString());
        this.messages.put(message.getUid(), message);
        Log.d("COnversation", "push message, after push messages length : " + this.messages.size());
    }

    @Exclude
    public String getContactPseudo() {
        String contactPseudo = "";
        String currentUserId = AccountService.getInstance()
                .getCurrentUser().getUid();

        for (User user : this.getUsers().values()) {
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
