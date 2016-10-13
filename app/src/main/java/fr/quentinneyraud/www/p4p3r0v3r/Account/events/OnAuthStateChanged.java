package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

/**
 * Created by quentin on 12/10/2016.
 */

public class OnAuthStateChanged {

    public Boolean connected;
    public String uid;

    public OnAuthStateChanged() {
    }

    public OnAuthStateChanged(Boolean connected, String uid) {
        this.connected = connected;
        this.uid = uid;
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
