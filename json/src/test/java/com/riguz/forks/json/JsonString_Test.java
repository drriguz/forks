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

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static com.riguz.forks.json.TestUtil.assertException;
import static com.riguz.forks.json.TestUtil.serializeAndDeserialize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JsonString_Test {

    private StringWriter stringWriter;
    private JsonWriter jsonWriter;

    @Before
    public void setUp() {
        stringWriter = new StringWriter();
        jsonWriter = new JsonWriter(stringWriter);
    }

    @Test
    public void constructor_failsWithNull() {
        assertException(NullPointerException.class, "string is null", new Runnable() {
            @Override
            public void run() {
                new com.riguz.forks.json.JsonString(null);
            }
        });
    }

    @Test
    public void write() throws IOException {
        new com.riguz.forks.json.JsonString("foo").write(jsonWriter);

        assertEquals("\"foo\"", stringWriter.toString());
    }

    @Test
    public void write_escapesStrings() throws IOException {
        new com.riguz.forks.json.JsonString("foo\\bar").write(jsonWriter);

        assertEquals("\"foo\\\\bar\"", stringWriter.toString());
    }

    @Test
    public void isString() {
        assertTrue(new com.riguz.forks.json.JsonString("foo").isString());
    }

    @Test
    public void asString() {
        assertEquals("foo", new com.riguz.forks.json.JsonString("foo").asString());
    }

    @Test
    public void equals_trueForSameInstance() {
        com.riguz.forks.json.JsonString string = new com.riguz.forks.json.JsonString("foo");

        assertTrue(string.equals(string));
    }

    @Test
    public void equals_trueForEqualStrings() {
        assertTrue(new com.riguz.forks.json.JsonString("foo").equals(new com.riguz.forks.json.JsonString("foo")));
    }

    @Test
    public void equals_falseForDifferentStrings() {
        assertFalse(new com.riguz.forks.json.JsonString("").equals(new com.riguz.forks.json.JsonString("foo")));
        assertFalse(new com.riguz.forks.json.JsonString("foo").equals(new com.riguz.forks.json.JsonString("bar")));
    }

    @Test
    public void equals_falseForNull() {
        assertFalse(new com.riguz.forks.json.JsonString("foo").equals(null));
    }

    @Test
    public void equals_falseForSubclass() {
        assertFalse(new com.riguz.forks.json.JsonString("foo").equals(new com.riguz.forks.json.JsonString("foo") {
        }));
    }

    @Test
    public void hashCode_equalsForEqualStrings() {
        assertTrue(
            new com.riguz.forks.json.JsonString("foo").hashCode() == new com.riguz.forks.json.JsonString("foo")
                .hashCode());
    }

    @Test
    public void hashCode_differsForDifferentStrings() {
        assertFalse(new com.riguz.forks.json.JsonString("").hashCode() == new com.riguz.forks.json.JsonString("foo")
            .hashCode());
        assertFalse(
            new com.riguz.forks.json.JsonString("foo").hashCode() == new com.riguz.forks.json.JsonString("bar")
                .hashCode());
    }

    @Test
    public void canBeSerializedAndDeserialized() throws Exception {
        com.riguz.forks.json.JsonString string = new com.riguz.forks.json.JsonString("foo");

        assertEquals(string, serializeAndDeserialize(string));
    }

}
