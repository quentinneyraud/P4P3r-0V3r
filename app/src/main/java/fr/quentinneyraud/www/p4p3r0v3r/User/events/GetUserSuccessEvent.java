package fr.quentinneyraud.www.p4p3r0v3r.User.events;

import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;
import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractSuccessEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class GetUserSuccessEvent extends AbstractSuccessEvent {

    private User user;

    public GetUserSuccessEvent() {
        super();
    }

    public GetUserSuccessEvent(User user) {
        this.setUser(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GetUserSuccessEvent{" +
                "user=" + user +
                '}';
    }
}
