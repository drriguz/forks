package com.riguz.forks.http.integration;


import com.riguz.forks.http.integration.util.CpuCoreSensor;

public class Options {
    public static final int DEFAULT_EVENT_LOOP_POOL_SIZE = 2 * CpuCoreSensor.availableProcessors();

    private final int eventLoopPoolSize;
    private final boolean useNativeTransport;

    public Options(int eventLoopPoolSize,
                   boolean useNativeTransport) {
        this.eventLoopPoolSize = eventLoopPoolSize;
        this.useNativeTransport = useNativeTransport;
    }

    public Options() {
        this(DEFAULT_EVENT_LOOP_POOL_SIZE,
                true);
    }

    public int getEventLoopPoolSize() {
        return eventLoopPoolSize;
    }

    public boolean isUseNativeTransport() {
        return useNativeTransport;
    }
}
