package com.riguz.forks.config;

import com.riguz.forks.config.property.PropertyParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayTypeTest {

    @Test
    public void parseBool() {
        String rule = "scope default{ bool ssl = [false, true];};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(false, parser.get("ssl"));
        assertEquals(true, parser.get("unsafe"));
    }

    @Test
    public void parseInt() {
        String rule = "scope default{ int version = 19;};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(19, (int) parser.get("version"));
    }

    @Test
    public void parseDecimal() {
        String rule = "scope default{ decimal pi = 3.14159;};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(3.14159, parser.<Double>get("pi"), 0);
    }

    @Test
    public void parseString() {
        String rule = "scope default{ string msg = \"Hello World!\";};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals("Hello World!", parser.get("msg"));
    }
}
