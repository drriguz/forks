package com.riguz.forks.router;

import com.riguz.forks.router.old.Router;

public interface RouteLoader<T> {

    Router<T> load();
}
