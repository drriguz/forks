package com.riguz.forks.router;


import com.riguz.forks.http.Router;

public interface RouteLoader<T> {

    Router<T> load();
}
