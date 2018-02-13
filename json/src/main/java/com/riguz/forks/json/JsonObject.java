/*******************************************************************************
 * Copyright (c) 2013, 2015 EclipseSource.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.riguz.forks.json;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class JsonObject extends JsonValue implements Iterable<JsonObject.Member> {

    private final List<String> names;
    private final List<JsonValue> values;
    private transient HashIndexTable table;

    public JsonObject() {
        names = new ArrayList<>();
        values = new ArrayList<>();
        table = new HashIndexTable();
    }

    public JsonObject(JsonObject object) {
        this(object, false);
    }

    private JsonObject(JsonObject object, boolean unmodifiable) {
        if (object == null) {
            throw new NullPointerException("object is null");
        }
        if (unmodifiable) {
            names = Collections.unmodifiableList(object.names);
            values = Collections.unmodifiableList(object.values);
        } else {
            names = new ArrayList<>(object.names);
            values = new ArrayList<>(object.values);
        }
        table = new HashIndexTable();
        updateHashIndex();
    }

    public static JsonObject unmodifiableObject(JsonObject object) {
        return new JsonObject(object, true);
    }

    public JsonObject add(String name, int value) {
        add(name, Json.value(value));
        return this;
    }

    public JsonObject add(String name, long value) {
        add(name, Json.value(value));
        return this;
    }

    public JsonObject add(String name, float value) {
        add(name, Json.value(value));
        return this;
    }

    public JsonObject add(String name, double value) {
        add(name, Json.value(value));
        return this;
    }

    public JsonObject add(String name, boolean value) {
        add(name, Json.value(value));
        return this;
    }

    public JsonObject add(String name, String value) {
        add(name, Json.value(value));
        return this;
    }

    public JsonObject add(String name, JsonValue value) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }
        if (value == null) {
            throw new NullPointerException("value is null");
        }
        table.add(name, names.size());
        names.add(name);
        values.add(value);
        return this;
    }

    public JsonObject set(String name, int value) {
        set(name, Json.value(value));
        return this;
    }

    public JsonObject set(String name, long value) {
        set(name, Json.value(value));
        return this;
    }

    public JsonObject set(String name, float value) {
        set(name, Json.value(value));
        return this;
    }

    public JsonObject set(String name, double value) {
        set(name, Json.value(value));
        return this;
    }

    public JsonObject set(String name, boolean value) {
        set(name, Json.value(value));
        return this;
    }

    public JsonObject set(String name, String value) {
        set(name, Json.value(value));
        return this;
    }

    public JsonObject set(String name, JsonValue value) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }
        if (value == null) {
            throw new NullPointerException("value is null");
        }
        int index = indexOf(name);
        if (index != -1) {
            values.set(index, value);
        } else {
            table.add(name, names.size());
            names.add(name);
            values.add(value);
        }
        return this;
    }

    public JsonObject remove(String name) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }
        int index = indexOf(name);
        if (index != -1) {
            table.remove(index);
            names.remove(index);
            values.remove(index);
        }
        return this;
    }

    public JsonObject merge(JsonObject object) {
        if (object == null) {
            throw new NullPointerException("object is null");
        }
        for (Member member : object) {
            this.set(member.name, member.value);
        }
        return this;
    }

    public JsonValue get(String name) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }
        int index = indexOf(name);
        return index != -1 ? values.get(index) : null;
    }

    public int getInt(String name, int defaultValue) {
        JsonValue value = get(name);
        return value != null ? value.asInt() : defaultValue;
    }

    public long getLong(String name, long defaultValue) {
        JsonValue value = get(name);
        return value != null ? value.asLong() : defaultValue;
    }

    public float getFloat(String name, float defaultValue) {
        JsonValue value = get(name);
        return value != null ? value.asFloat() : defaultValue;
    }

    public double getDouble(String name, double defaultValue) {
        JsonValue value = get(name);
        return value != null ? value.asDouble() : defaultValue;
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        JsonValue value = get(name);
        return value != null ? value.asBoolean() : defaultValue;
    }

    public String getString(String name, String defaultValue) {
        JsonValue value = get(name);
        return value != null ? value.asString() : defaultValue;
    }

    public int size() {
        return names.size();
    }

    public boolean isEmpty() {
        return names.isEmpty();
    }

    public List<String> names() {
        return Collections.unmodifiableList(names);
    }

    @Override
    public Iterator<Member> iterator() {
        final Iterator<String> namesIterator = names.iterator();
        final Iterator<JsonValue> valuesIterator = values.iterator();
        return new Iterator<Member>() {

            @Override
            public boolean hasNext() {
                return namesIterator.hasNext();
            }

            @Override
            public Member next() {
                String name = namesIterator.next();
                JsonValue value = valuesIterator.next();
                return new Member(name, value);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    @Override
    void write(JsonWriter writer) throws IOException {
        writer.writeObjectOpen();
        Iterator<String> namesIterator = names.iterator();
        Iterator<JsonValue> valuesIterator = values.iterator();
        if (namesIterator.hasNext()) {
            writer.writeMemberName(namesIterator.next());
            writer.writeMemberSeparator();
            valuesIterator.next().write(writer);
            while (namesIterator.hasNext()) {
                writer.writeObjectSeparator();
                writer.writeMemberName(namesIterator.next());
                writer.writeMemberSeparator();
                valuesIterator.next().write(writer);
            }
        }
        writer.writeObjectClose();
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public JsonObject asObject() {
        return this;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + names.hashCode();
        result = 31 * result + values.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JsonObject other = (JsonObject) obj;
        return names.equals(other.names) && values.equals(other.values);
    }

    int indexOf(String name) {
        int index = table.get(name);
        if (index != -1 && name.equals(names.get(index))) {
            return index;
        }
        return names.lastIndexOf(name);
    }

    private synchronized void readObject(ObjectInputStream inputStream)
        throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        table = new HashIndexTable();
        updateHashIndex();
    }

    private void updateHashIndex() {
        int size = names.size();
        for (int i = 0; i < size; i++) {
            table.add(names.get(i), i);
        }
    }

    public static class Member {

        private final String name;
        private final JsonValue value;

        Member(String name, JsonValue value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public JsonValue getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            int result = 1;
            result = 31 * result + name.hashCode();
            result = 31 * result + value.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null) {
                return false;
            }
            if (getClass() != object.getClass()) {
                return false;
            }
            Member other = (Member) object;
            return name.equals(other.name) && value.equals(other.value);
        }

    }

    static class HashIndexTable {

        private final byte[] hashTable = new byte[32]; // must be a power of two

        public HashIndexTable() {
        }

        public HashIndexTable(HashIndexTable original) {
            System.arraycopy(original.hashTable, 0, hashTable, 0, hashTable.length);
        }

        void add(String name, int index) {
            int slot = hashSlotFor(name);
            if (index < 0xff) {
                // increment by 1, 0 stands for empty
                hashTable[slot] = (byte) (index + 1);
            } else {
                hashTable[slot] = 0;
            }
        }

        void remove(int index) {
            for (int i = 0; i < hashTable.length; i++) {
                if (hashTable[i] == index + 1) {
                    hashTable[i] = 0;
                } else if (hashTable[i] > index + 1) {
                    hashTable[i]--;
                }
            }
        }

        int get(Object name) {
            int slot = hashSlotFor(name);
            // subtract 1, 0 stands for empty
            return (hashTable[slot] & 0xff) - 1;
        }

        private int hashSlotFor(Object element) {
            return element.hashCode() & hashTable.length - 1;
        }

    }

}
