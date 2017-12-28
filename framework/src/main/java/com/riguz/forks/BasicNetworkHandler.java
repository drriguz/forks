package com.riguz.forks;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.http.NetworkHandler;

public class BasicNetworkHandler implements NetworkHandler {

    @Override
    public void dispatch(HttpRequest request, HttpResponse response) {
        System.out.println(request.getRequestURL());
        response.writeContent("Hello :" + request.getRequestURI());
    }
}
