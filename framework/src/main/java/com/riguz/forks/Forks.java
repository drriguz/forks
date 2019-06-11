package com.riguz.forks;

import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.ioc.Injector;
import com.riguz.forks.router.Router;

import javax.inject.Inject;

public final class Forks {

    private final Injector injector;

    public Forks(Object... configs) {
        this.injector = new Injector(configs);
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

    public synchronized void start() {
        if (this.started) {
            return;
        }
        this.networkServer.start();
        this.networkServer.afterStart();
        this.started = true;
    }

    public void stop() {
        this.networkServer.stop();
    }
}
