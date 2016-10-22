package fr.quentinneyraud.www.p4p3r0v3r.Search.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractErrorEvent;

/**
 * Created by Agustina on 19/10/2016.
 */

public class SearchUserErrorEvent extends AbstractErrorEvent {

    public SearchUserErrorEvent() {
        super();
    }

    public SearchUserErrorEvent(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "SearchUserErrorEvent()";
    }

}

