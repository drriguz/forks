package com.riguz.forks.http.integration;

import org.apache.commons.io.IOUtils;
import rawhttp.core.RawHttp;
import rawhttp.core.RawHttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

public final class TestUtil {
    private static final RawHttp rawHttp = new RawHttp();

    public static RawHttpRequest parseRequest(String file, Map<String, Object> args) throws IOException {
        try (InputStream in = TestUtil.class.getClassLoader().getResourceAsStream(file)) {
            String request = IOUtils.toString(in, StandardCharsets.UTF_8);
            for (String arg : args.keySet()) {
                request = request.replaceAll("\\{\\{" + arg + "\\}\\}", args.get(arg).toString());
            }
            // normalize
            request = request.replaceAll("\\\r\\\n", "\\\n")
                    .replaceAll("\\\n", "\\\r\\\n");
            return rawHttp.parseRequest(request);
        }
    }

    public static RawHttpRequest parseRequest(String file) throws IOException {
        return parseRequest(file, Collections.emptyMap());
    }
}
