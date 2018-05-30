package com.riguz.forks.json.simple;

import com.eclipsesource.json.Json;
import com.riguz.forks.json.JsonParser;
import com.riguz.forks.json.JsonValue;
import org.junit.Ignore;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.Consumer;

import static org.junit.Assert.assertNotNull;

@Ignore
public class LargeJsonTest {

    private long parse(Consumer<Reader> parser, String file) {
        InputStreamReader reader = new InputStreamReader(
                Thread
                        .currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(file));
        long start = System.currentTimeMillis();
        parser.accept(reader);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long parseLargeJson(String file) {
        return parse(reader -> {
            JsonParser parser = new JsonParser(4096);
            JsonValue value = parser.parse(reader);
            assertNotNull(value);
        }, file);
    }

    public long parseUseMinimalJson(String file) throws IOException {
        return parse(reader -> {
            try {
                com.eclipsesource.json.JsonValue value = Json.parse(reader);
                assertNotNull(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, file);
    }

    public static void main1(String[] args) throws IOException {
        String[] jsons = {"200k-min.json", "200k-4s.json", "500k-2s.json"};
        // String[] jsons = {"200k-min.json", "200k-4s.json", "sf-city-lots-json/citylots.json"};
        String maps = "Australia.json German.json Norway.json Spain.json Brazil.json Iceland.json Portugal.json Sweden.json Canada.json India.json Russia.json Swiss.json England.json Japan.json Singapore.json USA.json Finland.json Netheland.json SouthAfrica.json world.json France.json NewZealand.json SouthKorea.json";
        String[] mapItems = maps.split(" ");
        LargeJsonTest test = new LargeJsonTest();
        int times = 5;
        for (String json : mapItems) {
            json = "maps/" + json;
            System.out.println("------------------------> " + json);
            long total = 0;
            for (int i = 0; i < times; i++)
                total += test.parseLargeJson(json);
            System.out.println("[Forks  ] Total:" + total + "/" + times + " ms Avg:" + total / times);
            total = 0;
            for (int i = 0; i < times; i++)
                total += test.parseUseMinimalJson(json);
            System.out.println("[Minimal] Total:" + total + "/" + times + " ms Avg:" + total / times);
        }
    }

    public static void main(String[] args) {
        LargeJsonTest test = new LargeJsonTest();
        for (int i = 0; i < 10; i++) {
            long time = test.parseLargeJson("maps/world.json");
            System.out.println("Cost:" + time + " ms");
        }
    }
}
