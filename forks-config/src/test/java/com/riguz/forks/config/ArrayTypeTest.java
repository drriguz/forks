package com.riguz.forks.config;

import com.riguz.forks.config.property.PropertyParser;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.assertEquals;

public class ArrayTypeTest {

    @Test
    public void parseBool() {
        String rule = "scope default{ bool ssl = [false, true];};";
        PropertyParser parser = PropertyParser.fromString(rule);
        Boolean[] ssl = parser.get("ssl");
        assertEquals(2, ssl.length);
        assertEquals(false, ssl[0]);
        assertEquals(true, ssl[1]);
    }

    @Test
    public void parseInt() {
        String rule = "scope default{ int version = [19, 12];};";
        PropertyParser parser = PropertyParser.fromString(rule);
        Integer[] version = parser.get("version");
        assertEquals(19, (int) version[0]);
        assertEquals(12, (int) version[1]);
    }

    @Test
    public void parseFloat() {
        String rule = "scope default{ float pi = [3.14159, 3.14];};";
        PropertyParser parser = PropertyParser.fromString(rule);
        Float[] version = parser.get("pi");
        assertEquals(3.14159f, version[0], 0.0f);
        assertEquals(3.14f, version[1], 0.0f);
    }

    @Test
    public void parseString() {
        String rule = "scope default{ string msg = [\"Hello World!\", \"riguz\"];};";
        PropertyParser parser = PropertyParser.fromString(rule);
        String[] msg = parser.get("msg");
        assertEquals("Hello World!", msg[0]);
        assertEquals("riguz", msg[1]);
    }

    @Test
    public void parseShared() {
        String rule = "shared{ string name=\"riguz lee\";};"
                + "scope default{ string msg = [\"Hello World!\", \"riguz\", ${name}];};";
        PropertyParser parser = PropertyParser.fromString(rule);
        String[] msg = parser.get("msg");
        assertEquals("Hello World!", msg[0]);
        assertEquals("riguz", msg[1]);
        assertEquals("riguz lee", msg[2]);
    }
}
