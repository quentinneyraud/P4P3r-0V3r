package fr.quentinneyraud.www.p4p3r0v3r.User.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractErrorEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class getUserCancelledEvent extends AbstractErrorEvent {

    public getUserCancelledEvent() {
        super();
    }

    public getUserCancelledEvent(String errorMessage) {
        super(errorMessage);
    }
}
