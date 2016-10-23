package fr.quentinneyraud.www.p4p3r0v3r.Account.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractErrorEvent;


public class LogoutErrorEvent extends AbstractErrorEvent {

    public LogoutErrorEvent() {
        super();
    }

    public LogoutErrorEvent(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "LogoutErrorEvent{}";
    }
}
