package com.riguz.forks.json;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class DslJsonSerializer implements JsonSerializer {
    private static final DslJson<Object> dslJson = new DslJson<>();
    private static final ThreadLocal<JsonWriter> jsonWriterProvider = ThreadLocal.withInitial(dslJson::newWriter);

    @Override
    public byte[] serialize(Object original) throws IOException {
        final JsonWriter writer = jsonWriterProvider.get();
        writer.reset();
        dslJson.serialize(writer, original);
        return writer.toByteArray();
    }
}
