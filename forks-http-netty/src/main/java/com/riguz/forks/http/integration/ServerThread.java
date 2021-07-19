package com.riguz.forks.http.integration;

import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerThread extends FastThreadLocalThread {
    private final boolean worker;

    public ServerThread(Runnable target,
                        String name,
                        boolean worker) {
        super(target, name);
        this.worker = worker;
    }

    public static ThreadFactory createThreadFactory(String prefix) {
        return createThreadFactory(prefix, false);
    }

    public static ThreadFactory createThreadFactory(String prefix, boolean isWorker) {
        AtomicInteger threadCounter = new AtomicInteger(0);
        return runnable -> {
            ServerThread thread = new ServerThread(runnable, prefix + "_" + threadCounter.getAndIncrement(), isWorker);
            thread.setDaemon(false);
            return thread;
        };
    }
}
