package com.riguz.forks.config;

import com.riguz.forks.ioc.Bind;
import javax.inject.Singleton;

import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.http.RequestDelegate;
import com.riguz.forks.http.undertow.UndertowServer;
import com.riguz.forks.mvc.Dispatcher;
import com.riguz.forks.router.Router;
import com.riguz.forks.router.TrieRouter;

public class DefaultConfig {

	@Bind
	@Singleton
	public Router router(TrieRouter router) {
		return router;
	}

	@Bind
	@Singleton
	public RequestDelegate delegator(Dispatcher dispatcher) {
		return dispatcher;
	}

	@Bind
	@Singleton
	public NetworkServer server(RequestDelegate delegate) {
		return new UndertowServer(8080, delegate);
	}
}
