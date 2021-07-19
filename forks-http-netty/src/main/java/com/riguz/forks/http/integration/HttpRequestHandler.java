package com.riguz.forks.http.integration;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCounted;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpRequestHandler extends ChannelInboundHandlerAdapter {
    private static final HttpHeaders NO_TRAILING = EmptyHttpHeaders.INSTANCE;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Received:" + msg);
        try {
            if (msg instanceof HttpRequest) {
                System.out.println("Received request.");
                if (((HttpRequest) msg).decoderResult().isFailure()) {

                } else {
                    ByteBuf data = copiedBufferUtf8("Hello world!");
                    DefaultHttpHeaders headers = new DefaultHttpHeaders(true);
                    headers.set(CONTENT_LENGTH, Long.toString(data.readableBytes()));
                    DefaultFullHttpResponse response = new DefaultFullHttpResponse(
                            HTTP_1_1,
                            HttpResponseStatus.OK,
                            data,
                            headers,
                            NO_TRAILING
                    );
                    ctx.writeAndFlush(response);
                }
            } else if (msg instanceof HttpContent) {
                System.out.println("Content received.");
            } else {
                throw new IllegalStateException("Unexpected message received");
            }
        } finally {
            if (msg instanceof ReferenceCounted) {
                ReferenceCounted ref = (ReferenceCounted) msg;
                if (ref.refCnt() > 0) {
                    ref.release();
                }
            }
        }
    }

    private static final ByteBufAllocator ALLOC = UnpooledByteBufAllocator.DEFAULT;

    private static ByteBuf copiedBufferUtf8(CharSequence string) {
        boolean release = true;
        // Mimic the same behavior as other copiedBuffer implementations.
        ByteBuf buffer = ALLOC.heapBuffer(ByteBufUtil.utf8Bytes(string));
        try {
            ByteBufUtil.writeUtf8(buffer, string);
            release = false;
            return buffer;
        } finally {
            if (release) {
                buffer.release();
            }
        }
    }
}
