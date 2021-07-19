package com.riguz.forks.http.integration;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class RequestPipeline extends ChannelInitializer<SocketChannel> {
    private final int maxChunkSize;

    public RequestPipeline(int maxChunkSize) {
        this.maxChunkSize = maxChunkSize;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        HttpServerCodec codec = new HttpServerCodec(4096, 8192, maxChunkSize, false);
        pipeline.addLast("codec", codec);
        pipeline.addLast("handler", new HttpRequestHandler());
    }
}
