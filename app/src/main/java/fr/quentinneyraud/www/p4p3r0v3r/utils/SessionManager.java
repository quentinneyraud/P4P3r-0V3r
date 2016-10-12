package fr.quentinneyraud.www.p4p3r0v3r.utils;

import fr.quentinneyraud.www.p4p3r0v3r.User.User;

/**
 * Created by quentin on 11/10/2016.
 */

public class SessionManager {

    private User user;
    private static SessionManager instance;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }

        return instance;
    }
}
