package com.riguz.forks.json.fast;

import com.riguz.forks.json.JsonParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
