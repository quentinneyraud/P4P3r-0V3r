package fr.quentinneyraud.www.p4p3r0v3r.User;

import android.util.Log;

import java.util.List;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.Conversation;

/**
 * Created by quentin on 11/10/2016.
 */

public class User {

    static final String TAG = "=== User ===";

    private String uid;
    private String pseudo;
    private String email;
    private String password;
    private String publicKey;
    private String avatarB64;
    private List<Conversation> conversations;

    public User() {

        Log.d(TAG, "Create new User" + this.toString());
    }

    public User(String uid) {
        this.uid = uid;

        Log.d(TAG, "Create new User" + this.toString());
    }

    public User(String uid, String pseudo, String email, String password) {
        this.setUid(uid);
        this.setPseudo(pseudo);
        this.setEmail(email);
        this.setPassword(password);

        Log.d(TAG, "Create new User" + this.toString());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getAvatarB64() {
        return avatarB64;
    }

    public void setAvatarB64(String avatarB64) {
        this.avatarB64 = avatarB64;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
