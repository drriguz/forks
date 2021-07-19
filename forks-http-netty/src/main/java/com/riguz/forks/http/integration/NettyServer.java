package com.riguz.forks.http.integration;

import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.http.RequestDelegate;

public class NettyServer extends NetworkServer {
    public NettyServer(int port, RequestDelegate handler) {
        super(port, handler);
    }

    @Override
    public void start() {

    }

    @Override
    public void afterStart() {

    }

    @Override
    public void stop() {

    }
}
