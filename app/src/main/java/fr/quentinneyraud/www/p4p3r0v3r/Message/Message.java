package fr.quentinneyraud.www.p4p3r0v3r.Message;

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
    private Date timestamp;

    public Message() {
    }

    public Message(String uid, String message, String userUid, Date timestamp) {
        this.uid = uid;
        this.message = message;
        this.userUid = userUid;
        this.timestamp = timestamp;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getFormattedDate(String format){
        return new SimpleDateFormat(format, Locale.FRANCE).format(this.getTimestamp());
    }

    public String getFormattedDate(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(this.getTimestamp());
    }
}
