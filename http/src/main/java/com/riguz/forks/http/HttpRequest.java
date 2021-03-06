package com.riguz.forks.http;

import java.io.InputStream;

public interface HttpRequest extends Routable {

    boolean isSecure();

    String getRequestQuery();

    String getRequestURI();

    String getRequestURL();

    InputStream getInputStream();

    String getParamValue(String name);

    String[] getParamValues(String name);

    Iterable<String> getParamNames();
}
