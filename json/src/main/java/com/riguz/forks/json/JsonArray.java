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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class JsonArray extends JsonValue implements Iterable<JsonValue> {

    private final List<JsonValue> values;

    public JsonArray() {
        values = new ArrayList<>();
    }

    public JsonArray(JsonArray array) {
        this(array, false);
    }

    private JsonArray(JsonArray array, boolean unmodifiable) {
        if (array == null) {
            throw new NullPointerException("array is null");
        }
        if (unmodifiable) {
            values = Collections.unmodifiableList(array.values);
        } else {
            values = new ArrayList<>(array.values);
        }
    }

    public static JsonArray unmodifiableArray(JsonArray array) {
        return new JsonArray(array, true);
    }

    public JsonArray add(int value) {
        values.add(Json.value(value));
        return this;
    }

    public JsonArray add(long value) {
        values.add(Json.value(value));
        return this;
    }

    public JsonArray add(float value) {
        values.add(Json.value(value));
        return this;
    }

    public JsonArray add(double value) {
        values.add(Json.value(value));
        return this;
    }

    public JsonArray add(boolean value) {
        values.add(Json.value(value));
        return this;
    }

    public JsonArray add(String value) {
        values.add(Json.value(value));
        return this;
    }

    public JsonArray add(JsonValue value) {
        if (value == null) {
            throw new NullPointerException("value is null");
        }
        values.add(value);
        return this;
    }

    public JsonArray set(int index, int value) {
        values.set(index, Json.value(value));
        return this;
    }

    public JsonArray set(int index, long value) {
        values.set(index, Json.value(value));
        return this;
    }

    public JsonArray set(int index, float value) {
        values.set(index, Json.value(value));
        return this;
    }

    public JsonArray set(int index, double value) {
        values.set(index, Json.value(value));
        return this;
    }

    public JsonArray set(int index, boolean value) {
        values.set(index, Json.value(value));
        return this;
    }

    public JsonArray set(int index, String value) {
        values.set(index, Json.value(value));
        return this;
    }

    public JsonArray set(int index, JsonValue value) {
        if (value == null) {
            throw new NullPointerException("value is null");
        }
        values.set(index, value);
        return this;
    }

    public JsonArray remove(int index) {
        values.remove(index);
        return this;
    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public JsonValue get(int index) {
        return values.get(index);
    }

    public List<JsonValue> values() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public Iterator<JsonValue> iterator() {
        final Iterator<JsonValue> iterator = values.iterator();
        return new Iterator<JsonValue>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public JsonValue next() {
                return iterator.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    void write(JsonWriter writer) throws IOException {
        writer.writeArrayOpen();
        Iterator<JsonValue> iterator = iterator();
        if (iterator.hasNext()) {
            iterator.next().write(writer);
            while (iterator.hasNext()) {
                writer.writeArraySeparator();
                iterator.next().write(writer);
            }
        }
        writer.writeArrayClose();
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public JsonArray asArray() {
        return this;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
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
        JsonArray other = (JsonArray) object;
        return values.equals(other.values);
    }

}
