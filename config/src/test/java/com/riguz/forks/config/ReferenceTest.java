package com.riguz.forks.config;

import com.riguz.forks.config.property.PropertyParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReferenceTest {

    String shared = "shared{bool https=false;int max=100;decimal xx=4.29;string author=\"riguz\";};";

    @Test
    public void parseReference() {
        String rule = shared + "scope default{ bool https = ${https}; "
                + "int max=${max};"
                + "float xx=${xx};"
                + "string author=${author};"
                + "string fullname=${author}..\" lee\";"
                + "bool unsafe = true;};";
        PropertyParser parser = PropertyParser.fromString(rule);
        assertEquals(false, parser.get("https"));
        assertEquals(100, (int) parser.get("max"));
        assertEquals(4.29, (double) parser.get("xx"), 0);
        assertEquals("riguz", (String) parser.get("author"));
        assertEquals("riguz lee", (String) parser.get("fullname"));
    }

    @Test(expected = InvalidValueException.class)
    public void parseNoDefined() {
        String rule = shared + "scope default{string name=${abc};};";
        PropertyParser parser = PropertyParser.fromString(rule);
    }

    @Test(expected = InvalidValueException.class)
    public void parseNoDefinedInString() {
        String rule = shared + "scope default{string name=\"hello\"..${abc};};";
        PropertyParser parser = PropertyParser.fromString(rule);
    }
}
