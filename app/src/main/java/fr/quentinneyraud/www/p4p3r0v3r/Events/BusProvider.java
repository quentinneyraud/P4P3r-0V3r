package fr.quentinneyraud.www.p4p3r0v3r.Events;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by quentin on 12/10/2016.
 */

public class BusProvider {

    private static final Bus instance = new Bus(ThreadEnforcer.MAIN);

    public static Bus getInstance() {
        return instance;
    }
}
