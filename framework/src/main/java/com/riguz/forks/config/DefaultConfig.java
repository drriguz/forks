package com.riguz.forks.config;

import com.riguz.forks.BasicNetworkHandler;
import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.http.undertow.UndertowServer;
import com.riguz.forks.router.Router;
import com.riguz.forks.router.TrieRouter;
import com.riguz.forks.ioc.Provides;
import javax.inject.Singleton;

public class DefaultConfig {

    @Provides
    @Singleton
    public Router router() {
        return new TrieRouter();
    }

    @Provides
    @Singleton
    public NetworkServer server() {
        return new UndertowServer(8080, new BasicNetworkHandler());
    }
}
