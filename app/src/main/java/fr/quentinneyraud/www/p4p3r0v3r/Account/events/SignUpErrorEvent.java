package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractErrorEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class SignUpErrorEvent extends AbstractErrorEvent {

    public SignUpErrorEvent() {
        super();
    }

    public SignUpErrorEvent(String errorMessage) {
        super(errorMessage);
    }
}
