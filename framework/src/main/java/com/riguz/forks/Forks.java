package com.riguz.forks;

import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.ioc.Injector;
import javax.inject.Inject;

import com.riguz.forks.router.Router;

public final class Forks {

    private final Injector injector;

    public Forks(final Injector injector) {
        this.injector = injector;
        this.injector.injectFields(this);
    }

    @Inject
    private Router router;

    @Inject
    private NetworkServer networkServer;

    private Forks() {
        this.injector = null;
    }

    private boolean started = false;

    public void start() {
        if (this.started) {
            return;
        }
        this.networkServer.start();
        this.networkServer.afterStart();
        this.started = true;
    }

    public static void stop() {

    }
}
