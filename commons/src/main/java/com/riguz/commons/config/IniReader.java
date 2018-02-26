package com.riguz.commons.config;

import com.riguz.commons.base.Strings;
import com.riguz.commons.io.Files;
import com.riguz.forks.json.Json;
import com.riguz.forks.json.JsonValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IniReader {

    static class Section {

        final Map<String, String> properties = new HashMap<>();

        void put(String key, String value) {
            this.properties.put(key, value);
        }

        String get(String key) {
            return this.properties.get(key);
        }
    }

    private final List<String> content;
    private final Map<String, Section> sections = new HashMap<>();

    public IniReader(String fileName) throws IOException {
        this.content = Files.readLines(fileName);
        this.parse();
    }

    private void parse() {
        Section lastSection = null;
        for (String line : this.content) {
            line = line.trim();
            if (Strings.isNullOrEmpty(line) || line.startsWith(";")) {
                continue;
            }
            if (line.matches("\\[\\w+\\]")) {
                String sectionName = line.substring(1, line.length() - 1);
                if (this.sections.containsKey(sectionName)) {
                    throw new IllegalArgumentException("Duplicate section:" + sectionName);
                }
                lastSection = new Section();
                this.sections.put(sectionName, lastSection);
            } else if (line.matches("\\w+\\s*=\\s*.*")) {
                String[] arr = line.split("=");
                String key = arr[0].trim();
                if (lastSection == null) {
                    throw new IllegalArgumentException("No section specified for:" + line);
                }
                lastSection.put(key, arr[1].trim());
            } else {
                throw new IllegalArgumentException("Invalid config file format:" + line);
            }
        }
    }

    public String get(String sectionName, String key) {
        Section section = this.sections.get(sectionName);
        if (section == null) {
            return null;
        }
        return section.get(key);
    }

    public JsonValue getJson(String section, String key) {
        String json = this.get(section, key);
        if (json == null) {
            return null;
        }
        return Json.parse(json);
    }
}
