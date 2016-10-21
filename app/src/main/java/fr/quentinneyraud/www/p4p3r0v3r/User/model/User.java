package fr.quentinneyraud.www.p4p3r0v3r.User.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by quentin on 11/10/2016.
 */

public class User {

    private String uid;
    private String pseudo;
    private String publicKey;
    private HashMap<String, String> conversations;

    public User() {
    }

    public User(String uid) {
        this.uid = uid;
    }

    public User(String uid, String pseudo) {
        this.setUid(uid);
        this.setPseudo(pseudo);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public HashMap<String, String> getConversations() {
        return conversations;
    }

    public void setConversations(HashMap<String, String> conversations) {
        this.conversations = conversations;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", pseudo='" + pseudo + '\'' +
                '}';
    }
}
