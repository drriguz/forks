package com.riguz.forks.config;

import com.riguz.forks.config.property.PropertyParser;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ParserTest {
    @Test
    public void basicTypes() throws IOException {
        PropertyParser parser = PropertyParser.fromResource("example.cf");
        assertNull(parser.get("notexists"));
        assertEquals(true, parser.get("forceHttps"));
        assertEquals(10, (int) parser.get("maxConnections"));
        assertEquals(3.1415926, (double) parser.get("pi"), 0);
        assertEquals("我能..,吞下(玻璃)而不伤身体!", parser.get("welcomeMessage"));
        assertEquals("我能..,吞下(玻璃)而不伤身体!", parser.get("message"));
        assertEquals("http://localhost:8080", parser.get("_baseUrl"));
        assertEquals("http://localhost:8080/admin", parser.get("adminUrl"));
        assertEquals("${_baseUrl}/guest", parser.get("guestUrl"));
        assertEquals("HelloWorldHelloWorldhttp://localhost:8080/admin", parser.get("all"));

        Integer[] workDays = parser.get("workDays");
        assertEquals(3, workDays.length);
        assertEquals(3, workDays[2].intValue());

        String[] guys = parser.get("guys");
        assertEquals(4, guys.length);
        assertEquals("魔礼红", guys[3]);

        Map<String, Object> user = parser.get("user");
        assertNotNull(user);
        assertEquals("Riguz", user.get("firstName"));
    }
}
