package com.riguz.forks.json;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class ParseSimpleJson {
    JsonParser parser = new JsonParser();

    @Test
    public void parseEmptyObject() throws IOException {
        parser.parse("{}");
        parser.parse("{\"name\":\"riguz\", \"age\":18}");
    }

    @Test
    public void parseBytes() {
        String s = "XXXXXXXXXXHelloWorld\n中国YYYYYYYYYY";
        byte[] bytes = s.getBytes();
        System.out.println(bytes.length);
        System.out.println(new String(bytes, 10, 14));
    }

    @Test
    public void bufferedRead() throws IOException {
        JsonParser p = new JsonParser(15);
        assertEquals("abcdefghijklmnopqrstuvwxyz", p.parse("\"abcdefghijklmnopqrstuvwxyz\""));
    }
}
