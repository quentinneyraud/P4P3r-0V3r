package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractErrorEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class SignInErrorEvent extends AbstractErrorEvent {

    public SignInErrorEvent() {
        super();
    }

    public SignInErrorEvent(String errorMessage) {
        super(errorMessage);
    }
}
