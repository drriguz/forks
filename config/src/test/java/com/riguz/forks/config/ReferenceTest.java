package com.riguz.forks.config;

import com.riguz.forks.config.property.PropertyParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReferenceTest {

    String shared = "shared{bool https=false;int max=100;decimal xx=4.29;string author=\"riguz\";};";

    @Test
    public void parseBool() {
        String rule = shared + "scope default{ bool ssl = ${https}; bool unsafe = true;};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(false, parser.get("ssl"));
        assertEquals(true, parser.get("unsafe"));
    }

    @Test
    public void parseInt() {
        String rule = shared + "scope default{ int version = ${max};};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(100, (int) parser.get("version"));
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
