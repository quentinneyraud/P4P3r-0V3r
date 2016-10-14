package fr.quentinneyraud.www.p4p3r0v3r.User.events;

import fr.quentinneyraud.www.p4p3r0v3r.utils.AbstractSuccessEvent;

/**
 * Created by quentin on 14/10/2016.
 */

public class SaveUserSuccessEvent extends AbstractSuccessEvent {

    public SaveUserSuccessEvent() {
        super();
    }

    @Override
    public String toString() {
        return "SaveUserSuccessEvent{}";
    }
}
