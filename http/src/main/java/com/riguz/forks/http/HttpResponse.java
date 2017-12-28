package com.riguz.forks.http;

import java.io.OutputStream;
import java.nio.ByteBuffer;

public interface HttpResponse {
    HttpResponse setContentLength(long length);

    HttpResponse writeContent(String content);

    HttpResponse writeContent(ByteBuffer byteBuffer);

    OutputStream getOutputStream();

    void flash();

    HttpResponse sendError(int status, String message);

    HttpResponse sendError(int status);

    HttpResponse sendRedirect(String url);

    HttpResponse setHeader(String name, String value);

    HttpResponse setStatus(int status);
}
