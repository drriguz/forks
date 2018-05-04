package com.riguz.forks.mvc;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;

public interface Resolver<T> {
    void resolveException(HttpRequest request, HttpResponse response, T content);
}
