package com.riguz.forks.http.integration;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static com.riguz.forks.http.integration.ServerThread.createThreadFactory;


public final class NettyTransportFactory {
    private final boolean epollEnabled;
    private final boolean kqueueEnabled;


    public NettyTransportFactory(boolean useNativeTransport) {
        this.epollEnabled = useNativeTransport && Epoll.isAvailable();
        this.kqueueEnabled = useNativeTransport && !Epoll.isAvailable() && KQueue.isAvailable();
    }

    public boolean isEpollEnabled() {
        return epollEnabled;
    }

    public boolean isKqueueEnabled() {
        return kqueueEnabled;
    }

    public ServerBootstrap configure(ServerBootstrap serverBootstrap) {
        if (isEpollEnabled()) {
            return serverBootstrap.channel(EpollServerSocketChannel.class)
                    .option(EpollChannelOption.SO_REUSEPORT, true);
        } else if (isKqueueEnabled())
            return serverBootstrap.channel(KQueueServerSocketChannel.class);
        else
            return serverBootstrap.channel(NioServerSocketChannel.class);
    }

    public EventLoopGroup createEventLoopGroup(
            int threads,
            String prefix,
            int idRatio) {
        if (epollEnabled) {
            // EpollEventLoopGroup#setIoRatio is deprecated
            return new EpollEventLoopGroup(threads, createThreadFactory(prefix + "-epoll"));
        } else if (kqueueEnabled) {
            KQueueEventLoopGroup instance = new KQueueEventLoopGroup(threads, createThreadFactory(prefix + "-kqueue"));
            instance.setIoRatio(idRatio);
            return instance;
        } else {
            NioEventLoopGroup instance = new NioEventLoopGroup(threads, createThreadFactory(prefix));
            instance.setIoRatio(idRatio);
            return instance;
        }
    }
}
