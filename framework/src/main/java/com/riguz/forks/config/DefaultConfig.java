package com.riguz.forks.config;

import com.riguz.forks.router.Router;
import com.riguz.forks.router.TrieRouter;
import com.riguz.forks.router.ioc.Provides;
import javax.inject.Singleton;

public class DefaultConfig {

    @Provides
    @Singleton
    public Router router() {
        return new TrieRouter();
    }
}
