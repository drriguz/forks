package com.riguz.forks.config;

import com.riguz.forks.config.property.Property;
import com.riguz.forks.config.property.PropertyParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SimpleTypeTest {

    @Test
    public void parseBool() {
        String rule = "scope default{ bool ssl = false; bool unsafe = true;};";
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
    public void parseFloat() {
        String rule = "scope default{ float pi = 3.14159;};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(3.14159f, (float) parser.get("pi"), 0.0f);
    }

    @Test
    public void parseString() {
        String rule = "scope default{ string msg = \"Hello World!\";};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals("Hello World!", parser.get("msg"));
    }

    @Test(expected = DuplicateException.class)
    public void parseDuplicated() {
        String rule = "scope default{ string msg = \"Hello World!\";string msg = \"123\";};";
        PropertyParser parser = PropertyParser.fromString(rule);
    }

    @Test(expected = DuplicateException.class)
    public void parseDuplicatedInShared() {
        String rule = "shared{ string msg = \"Hello World!\";string msg = \"123\";};";
        PropertyParser parser = PropertyParser.fromString(rule);
    }

    @Test
    public void parseFile() throws IOException {
        PropertyParser parser = PropertyParser.fromResource("example.cf");
        assertEquals("lihaifeng", parser.get("dev_db", "user"));
    }
}
