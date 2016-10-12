package fr.quentinneyraud.www.p4p3r0v3r.Events;

/**
 * Created by quentin on 12/10/2016.
 */

public class OnAuthStatusChanged {

    public Boolean connected;
    public String uid;

    public OnAuthStatusChanged() {
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
