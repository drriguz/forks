package com.riguz.forks.config;

import com.riguz.forks.config.property.PropertyParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void basicTypes() throws IOException {
        PropertyParser parser = new PropertyParser("basic.cf");
        assertEquals(true, parser.get("forceHttps"));
        assertEquals(10, (int)parser.get("maxConnections"));
        assertEquals(3.1415926, (double)parser.get("pi"), 0);
        assertEquals("我能吞下玻璃而不伤身体", parser.get("welcomeMessage"));
    }
}
