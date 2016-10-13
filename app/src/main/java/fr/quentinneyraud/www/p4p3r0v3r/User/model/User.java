package fr.quentinneyraud.www.p4p3r0v3r.User.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import fr.quentinneyraud.www.p4p3r0v3r.Conversation.model.Conversation;
import fr.quentinneyraud.www.p4p3r0v3r.utils.Firebase;

/**
 * Created by quentin on 11/10/2016.
 */

public class User {

    private static final String TAG = "=== User ===";

    private String uid;
    private String pseudo;
    private String publicKey;
    private String avatarB64;
    private List<String> conversationsUid;

    public User() {
    }

    public User(String uid) {
        this.uid = uid;

        Log.d(TAG, "Create new User" + this.toString());
    }

    public User(String uid, String pseudo) {
        this.setUid(uid);
        this.setPseudo(pseudo);

        Log.d(TAG, "Create new User" + this.toString());
    }

    public void getFullFromFirebase() {
        Firebase.getInstance().getUser(this.getUid(), new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });
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

    public String getAvatarB64() {
        return avatarB64;
    }

    public void setAvatarB64(String avatarB64) {
        this.avatarB64 = avatarB64;
    }

    public List<String> getConversationsUid() {
        return conversationsUid;
    }

    public void setConversationsUid(List<String> conversationsUid) {
        this.conversationsUid = conversationsUid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", pseudo='" + pseudo + '\'' +
                '}';
    }
}
