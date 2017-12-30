package com.riguz.forks.http;

public abstract class NetworkServer {
	protected final int port;
	protected final RequestDelegator handler;

	public NetworkServer(int port, RequestDelegator handler) {
		this.port = port;
		this.handler = handler;
	}

	public abstract void start();

	public abstract void afterStart();

	public abstract void stop();
}
