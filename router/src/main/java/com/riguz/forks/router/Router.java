package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.old.Resolved;

public interface Router<T> {
    void register(HttpMethod method, String pattern, T handler);

    T resolve(HttpMethod method, String requestUrl);
}
