package com.riguz.forks.http;

public abstract class NetworkServer {
	protected final int port;
	protected final RequestDelegate handler;

	public NetworkServer(int port, RequestDelegate handler) {
		this.port = port;
		this.handler = handler;
	}

	public abstract void start();

	public abstract void afterStart();

	public abstract void stop();
}
