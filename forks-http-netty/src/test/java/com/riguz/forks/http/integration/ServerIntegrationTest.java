package com.riguz.forks.http.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rawhttp.core.EagerHttpResponse;
import rawhttp.core.RawHttp;
import rawhttp.core.RawHttpRequest;
import rawhttp.core.client.RawHttpClient;
import rawhttp.core.client.TcpRawHttpClient;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ServerIntegrationTest {
    private int port = 10000 + new Random().nextInt(1024);
    private HttpServer httpServer;

    @Before
    public void setUp() throws Exception {
        httpServer = new HttpServer(port);
        httpServer.start();
    }

    @After
    public void tearDown() throws Exception {
        httpServer.stop();
    }

    @Test
    public void httpGet() throws IOException {
        RawHttp rawHttp = new RawHttp();
        RawHttpClient<?> client = new TcpRawHttpClient();
        RawHttpRequest request = TestUtil.parseRequest("http1.1/basic-get.request",
                Map.of("port", port));
        EagerHttpResponse<?> response = client.send(request).eagerly();
        assertEquals(200, response.getStatusCode());
    }
}
