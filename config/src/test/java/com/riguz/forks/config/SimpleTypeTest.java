package com.riguz.forks.config;

import com.riguz.forks.config.property.PropertyParser;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class SimpleTypeTest {

    @Test
    public void parseBool() {
        String rule = "shared { bool ssl = false; bool unsafe = true;};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(false, parser.getShared("ssl"));
        assertEquals(true, parser.getShared("unsafe"));
    }

    @Test
    public void parseInt() {
        String rule = "shared { int version = 19;};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(19, (int) parser.getShared("version"));
    }

    @Test
    public void parseDecimal() {
        String rule = "shared { decimal pi = 3.14159;};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(3.14159, (double) parser.getShared("pi"), 0);
    }

    @Test
    public void parseString() {
        String rule = "shared { string msg = \"Hello World!\";};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals("Hello World!", parser.getShared("msg"));
    }
}
