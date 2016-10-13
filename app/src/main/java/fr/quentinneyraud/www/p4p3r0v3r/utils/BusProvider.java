package fr.quentinneyraud.www.p4p3r0v3r.utils;

import com.squareup.otto.Bus;

/**
 * Created by quentin on 12/10/2016.
 */

public class BusProvider {

    private static final Bus instance = new Bus();

    public static Bus getInstance() {
        return instance;
    }
}
