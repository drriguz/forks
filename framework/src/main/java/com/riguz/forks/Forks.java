package com.riguz.forks;

import com.riguz.forks.ioc.Injector;
import javax.inject.Inject;

import com.riguz.forks.config.DefaultConfig;
import com.riguz.forks.router.Router;

public final class Forks {

	public static Forks instance = new Forks();
	private static final Injector iocContext;

	@Inject
	private Router router;

	// @Inject
	// private NetworkServer networkServer;

	static {
		iocContext = new Injector(new DefaultConfig());
		iocContext.injectFields(Forks.instance);
	}

	private Forks() {

	}

	private void internalServe() {
		// this.networkServer.start();
		// this.networkServer.afterStart();
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
