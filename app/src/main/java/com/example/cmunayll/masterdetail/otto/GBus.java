package com.example.cmunayll.masterdetail.otto;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by cmunayll on 17/01/2018.
 */

public class GBus {

    private static Bus bus;

    public static Bus getBus() {
        if (bus == null) {
            bus = new Bus(ThreadEnforcer.ANY);
        }
        return bus;
    }

}
