package fr.quentinneyraud.www.p4p3r0v3r.utils;

import fr.quentinneyraud.www.p4p3r0v3r.User.model.User;

/**
 * Created by quentin on 14/10/2016.
 */

public class CurrentUser {

    private static User instance;

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }

        return instance;
    }
}
