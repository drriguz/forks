package com.riguz.forks.mvc;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionResolver implements Resolver<Exception> {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public void resolve(HttpRequest request, HttpResponse response, Exception exception) {
        logger.error("Resolving exception:{}", exception);
        response.sendError(500, exception.getMessage());
    }
}
