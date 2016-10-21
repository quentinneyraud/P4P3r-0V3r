package fr.quentinneyraud.www.p4p3r0v3r.Message.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by quentin on 11/10/2016.
 */

public class Message {

    static final String TAG = "=== Message ===";

    private String uid;
    private String message;
    private String userUid;
    private String createdAt;

    public Message() {
    }

    public Message(String uid, String message, String userUid, String timestamp) {
        this.uid = uid;
        this.message = message;
        this.userUid = userUid;
        this.createdAt = timestamp;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String timestamp) {
        this.createdAt = timestamp;
    }

    public String getFormattedDate(String format) {
        Date d = new Date();
        d.setTime(Long.parseLong(this.createdAt));
        return new SimpleDateFormat(format, Locale.FRANCE).format(d);
    }

    @Override
    public String toString() {
        return "Message{" +
                "uid='" + uid + '\'' +
                ", message='" + message + '\'' +
                ", userUid='" + userUid + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
