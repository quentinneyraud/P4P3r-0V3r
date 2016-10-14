package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractSuccessEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class UserUnauthenticatedEvent extends AbstractSuccessEvent {

    public UserUnauthenticatedEvent() {
    }

    @Override
    public String toString() {
        return "UserUnauthenticatedEvent{}";
    }
}
