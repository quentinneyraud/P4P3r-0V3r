package fr.quentinneyraud.www.p4p3r0v3r.Events;

import fr.quentinneyraud.www.p4p3r0v3r.User.User;

/**
 * Created by quentin on 12/10/2016.
 */

public class AddUserEvent {

    private User user;

    public AddUserEvent(User user) {
        this.user = user;
    }
}
