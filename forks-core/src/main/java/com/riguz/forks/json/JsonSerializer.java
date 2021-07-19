package com.riguz.forks.json;

import java.io.IOException;

public interface JsonSerializer {
    byte[] serialize(Object original) throws IOException;
}
