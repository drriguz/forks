package com.riguz.forks.json.fast;

import com.riguz.forks.json.JsonParser;
import com.riguz.forks.json.JsonValue;
import com.riguz.forks.json.SyntaxException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class JsonCheckerTest {
    private final String file;

    @Parameterized.Parameters
    public static List<String> getFiles() {
        List<String> files = new ArrayList<>(64);
        for (int i = 1; i <= 33; i++) {
            String file = "json_checker/fail" + i + ".json";
            files.add(file);
        }
        for (int i = 1; i <= 3; i++) {
            String file = "json_checker/pass" + i + ".json";
            files.add(file);
        }
        files.add("sf-city-lots-json/citylots.json");
        return files;
    }

    public JsonCheckerTest(String file) {
        this.file = file;
    }

    private final JsonParser parser = new JsonParser();

    @Test
    public void parse() throws IOException {
        boolean result = JsonChecker.check(new InputStreamReader(
                Thread
                        .currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(this.file)), 20);
        if (file.contains("fail"))
            assertFalse(result);
        else
            assertTrue(result);
    }
}
