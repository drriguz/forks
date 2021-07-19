package com.riguz.forks.http.integration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HttpServer {
    private final int port;
    private final Options options;
    private final NettyTransportFactory transportFactory;
    private final EventLoopGroup acceptorLoop;
    private final EventLoopGroup eventLoop;

    public HttpServer(int port) {
        this(port, new Options());
    }

    public HttpServer(int port, Options options) {
        this.port = port;
        this.options = options;
        this.transportFactory = new NettyTransportFactory(options.isUseNativeTransport());
        this.acceptorLoop = transportFactory.createEventLoopGroup(
                1, "forks.acceptor", 50);
        this.eventLoop = transportFactory.createEventLoopGroup(
                options.getEventLoopPoolSize(), "forks.eventloop", 100);
    }

    public void start() throws InterruptedException {
        ServerBootstrap serverBootstrap = transportFactory
                .configure(createServerBootstrap())
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new RequestPipeline(8192));

        ChannelFuture future = serverBootstrap.bind(port).sync();

    }

    public void stop() {
        acceptorLoop.shutdownGracefully();
        eventLoop.shutdownGracefully();
    }

    private ServerBootstrap createServerBootstrap() {
        return new ServerBootstrap()
                .group(acceptorLoop, eventLoop)
                .option(ChannelOption.SO_BACKLOG, 8192)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
    }
}
