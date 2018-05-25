package com.riguz.forks.json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class JsonCheckerTest {
    private final String file;

    @Parameterized.Parameters
    public static List<String> getFiles() {
        List<String> files = new ArrayList<>(64);
        for (int i = 1; i < 33; i++) {
            String file = "json_checker/fail" + i + ".json";
            files.add(file);
        }
        return files;
    }

    public JsonCheckerTest(String file) {
        this.file = file;
    }

    private final JsonParser parser = new JsonParser();

    @Test
    public void parseBadJson() {
        try {
            parser.parse(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(this.file)));
            if (file.contains("fail"))
                fail("Should fail:" + file);
        } catch (SyntaxException e) {
            if (file.contains("pass"))
                fail("Should pass:" + file);
        }
    }
}
