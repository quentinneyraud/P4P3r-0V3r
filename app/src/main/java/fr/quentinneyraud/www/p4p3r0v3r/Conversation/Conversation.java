package fr.quentinneyraud.www.p4p3r0v3r.Conversation;

import android.util.Log;

import java.util.List;

import fr.quentinneyraud.www.p4p3r0v3r.Message.Message;

/**
 * Created by quentin on 11/10/2016.
 */

public class Conversation {

    static final String TAG = "=== Conversation ===";

    private String uid;
    private List<Message> messages;

    public Conversation() {
        Log.d(TAG, "Create new Conversation" + this.toString());
    }

    public Conversation(String uid) {
        this.uid = uid;

        Log.d(TAG, "Create new Conversation" + this.toString());
    }

    public Conversation(String uid, List<Message> messages) {
        this.uid = uid;
        this.messages = messages;

        Log.d(TAG, "Create new Conversation" + this.toString());
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "uid='" + uid + '\'' +
                ", messages=" + messages +
                '}';
    }
}
