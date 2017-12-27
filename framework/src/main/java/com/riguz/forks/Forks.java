package com.riguz.forks;

import com.riguz.forks.config.DefaultConfig;
import com.riguz.forks.router.Router;
import com.riguz.forks.router.ioc.IocContext;
import javax.inject.Inject;

public final class Forks {

    public static Forks instance = new Forks();
    private static final IocContext iocContext;

    @Inject
    private Router router;

    static {
        iocContext = new IocContext(new DefaultConfig());
        iocContext.injectFields(Forks.instance);
    }

    public static void serve() {
        System.out.println(instance.router);
    }

    public static void main(String[] args) {
        Forks.serve();
    }
}
