package com.riguz.forks.json.fast;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleTest {
    @Test
    public void pass() throws IOException {
        assertTrue(JsonChecker.check("{}", 20));
        assertTrue(JsonChecker.check("{\"hello\":123.0}", 20));
    }

    @Test
    public void fail() {
    }
}
