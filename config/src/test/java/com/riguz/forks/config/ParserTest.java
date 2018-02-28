package com.riguz.forks.config;

import org.junit.Test;

import java.io.IOException;

public class ParserTest {

    @Test
    public void parseBasicConfig() throws IOException {
        Parser parser = new Parser();
        parser.parse("types.cf");
        parser.parse("basic.cf");
    }
}
