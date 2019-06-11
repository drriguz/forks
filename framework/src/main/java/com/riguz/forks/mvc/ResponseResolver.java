package com.riguz.forks.mvc;

import com.riguz.forks.exceptions.UnexpectedException;
import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.json.JsonSerializer;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ResponseResolver implements Resolver<Object> {
    private final JsonSerializer jsonSerializer;

    @Inject
    public ResponseResolver(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public void resolve(HttpRequest request, HttpResponse response, Object result) {
        if (result == null) {
        } else {
            try {
                final byte[] serialized = jsonSerializer.serialize(result);
                response.writeContent(ByteBuffer.wrap(serialized));
            } catch (IOException e) {
                throw new UnexpectedException(e);
            }
        }
    }
}
