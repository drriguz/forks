package com.riguz.forks.mvc;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExceptionResolver implements Resolver<Exception> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionResolver.class);

    @Override
    public void resolveException(HttpRequest request, HttpResponse response, Exception exception) {
        logger.error("Resolving exception:{}", exception);
        response.sendError(500, exception.getMessage());
    }
}
