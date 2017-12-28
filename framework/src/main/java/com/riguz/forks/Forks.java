package com.riguz.forks;

import com.riguz.forks.config.DefaultConfig;
import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.router.Router;
import com.riguz.forks.ioc.IocContext;
import javax.inject.Inject;

public final class Forks {

    public static Forks instance = new Forks();
    private static final IocContext iocContext;

    @Inject
    private Router router;

    @Inject
    private NetworkServer networkServer;

    static {
        iocContext = new IocContext(new DefaultConfig());
        iocContext.injectFields(Forks.instance);
    }

    private void internalServe() {
        this.networkServer.start();
        this.networkServer.afterStart();
    }

    public static void serve() {
        instance.internalServe();
    }

    public static void stop() {

    }

    public static void main(String[] args) {
        Forks.serve();
    }
}
