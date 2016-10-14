package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractSuccessEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class UserAuthenticatedEvent extends AbstractSuccessEvent {

    private String uid;

    protected UserAuthenticatedEvent() {
        super();
    }

    public UserAuthenticatedEvent(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserAuthenticatedEvent{" +
                "uid='" + uid + '\'' +
                '}';
    }
}
