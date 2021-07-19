package com.riguz.forks.http.undertow;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.xnio.ChannelListener;
import org.xnio.ChannelListeners;
import org.xnio.OptionMap;
import org.xnio.Options;
import org.xnio.StreamConnection;
import org.xnio.Xnio;
import org.xnio.XnioWorker;
import org.xnio.channels.AcceptingChannel;

import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.http.RequestDelegate;

import io.undertow.UndertowOptions;
import io.undertow.connector.ByteBufferPool;
import io.undertow.server.DefaultByteBufferPool;
import io.undertow.server.protocol.http.HttpOpenListener;

public class UndertowServer extends NetworkServer {

    private static int cpuCores;

    private Xnio xnio = Xnio.getInstance();
    private XnioWorker worker;
    private AcceptingChannel<StreamConnection> server;

    static {
        cpuCores = Runtime.getRuntime().availableProcessors();
    }

    public UndertowServer(int port, RequestDelegate handler) {
        super(port, handler);
    }

    private void bootstrap() {
        HttpOpenListener httpListener = this.createHttpListener();
        httpListener.setRootHandler(
            exchange -> this.handler.delegate(new UndertowRequest(exchange), new UndertowResponse(exchange)));

        ChannelListener<AcceptingChannel<StreamConnection>> acceptListener = ChannelListeners
            .openListenerAdapter(httpListener);
        try {
            this.worker = this.xnio.createWorker(this.createWorkerOptions());
            this.server = this.worker.createStreamConnectionServer(new InetSocketAddress(this.port), acceptListener,
                this.createSocketOptions());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.server.resumeAccepts();
    }

    private HttpOpenListener createHttpListener() {
        ByteBufferPool bufferPool = new DefaultByteBufferPool(true, 8192);
        return new HttpOpenListener(bufferPool, this.createServerOptions());
    }

    private OptionMap createServerOptions() {
        return OptionMap.builder().set(UndertowOptions.BUFFER_PIPELINED_DATA, true)
            .set(UndertowOptions.ALWAYS_SET_KEEP_ALIVE, false).set(UndertowOptions.ALWAYS_SET_DATE, true)
            .set(UndertowOptions.RECORD_REQUEST_START_TIME, false)
            .set(UndertowOptions.NO_REQUEST_TIMEOUT, 60 * 1000).getMap();
    }

    private OptionMap createWorkerOptions() {
        int workerHighWater = 1000000;
        return OptionMap.builder().set(Options.WORKER_IO_THREADS, cpuCores * 2)
            .set(Options.CONNECTION_HIGH_WATER, workerHighWater).set(Options.CONNECTION_LOW_WATER, 1)
            .set(Options.WORKER_TASK_CORE_THREADS, cpuCores * 10)
            .set(Options.WORKER_TASK_MAX_THREADS, cpuCores * 15).set(Options.TCP_NODELAY, true)
            .set(Options.CORK, true).getMap();
    }

    private OptionMap createSocketOptions() {
        return OptionMap.builder().set(Options.WORKER_IO_THREADS, cpuCores * 2).set(Options.TCP_NODELAY, true)
            .set(Options.REUSE_ADDRESSES, true).getMap();
    }

    @Override
    public void start() {
        this.bootstrap();
    }

    @Override
    public void afterStart() {
    }

    @Override
    public void stop() {
        try {
            this.server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.worker.shutdownNow();
        }
    }
}
