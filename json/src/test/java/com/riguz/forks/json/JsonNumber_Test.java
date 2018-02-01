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

public class JsonNumber_Test {

    private StringWriter output;
    private JsonWriter writer;

    @Before
    public void setUp() {
        output = new StringWriter();
        writer = new JsonWriter(output);
    }

    @Test
    public void constructor_failsWithNull() {
        assertException(NullPointerException.class, "string is null", new Runnable() {
            @Override
            public void run() {
                new com.riguz.forks.json.JsonNumber(null);
            }
        });
    }

    @Test
    public void write() throws IOException {
        new com.riguz.forks.json.JsonNumber("23").write(writer);

        assertEquals("23", output.toString());
    }

    @Test
    public void toString_returnsInputString() {
        assertEquals("foo", new com.riguz.forks.json.JsonNumber("foo").toString());
    }

    @Test
    public void isNumber() {
        assertTrue(new com.riguz.forks.json.JsonNumber("23").isNumber());
    }

    @Test
    public void asInt() {
        assertEquals(23, new com.riguz.forks.json.JsonNumber("23").asInt());
    }

    @Test(expected = NumberFormatException.class)
    public void asInt_failsWithExceedingValues() {
        new com.riguz.forks.json.JsonNumber("10000000000").asInt();
    }

    @Test(expected = NumberFormatException.class)
    public void asInt_failsWithExponent() {
        new com.riguz.forks.json.JsonNumber("1e5").asInt();
    }

    @Test(expected = NumberFormatException.class)
    public void asInt_failsWithFractional() {
        new com.riguz.forks.json.JsonNumber("23.5").asInt();
    }

    @Test
    public void asLong() {
        assertEquals(23l, new com.riguz.forks.json.JsonNumber("23").asLong());
    }

    @Test(expected = NumberFormatException.class)
    public void asLong_failsWithExceedingValues() {
        new com.riguz.forks.json.JsonNumber("10000000000000000000").asLong();
    }

    @Test(expected = NumberFormatException.class)
    public void asLong_failsWithExponent() {
        new com.riguz.forks.json.JsonNumber("1e5").asLong();
    }

    @Test(expected = NumberFormatException.class)
    public void asLong_failsWithFractional() {
        new com.riguz.forks.json.JsonNumber("23.5").asLong();
    }

    @Test
    public void asFloat() {
        assertEquals(23.05f, new com.riguz.forks.json.JsonNumber("23.05").asFloat(), 0);
    }

    @Test
    public void asFloat_returnsInfinityForExceedingValues() {
        assertEquals(Float.POSITIVE_INFINITY, new com.riguz.forks.json.JsonNumber("1e50").asFloat(), 0);
        assertEquals(Float.NEGATIVE_INFINITY, new com.riguz.forks.json.JsonNumber("-1e50").asFloat(), 0);
    }

    @Test
    public void asDouble() {
        double result = new com.riguz.forks.json.JsonNumber("23.05").asDouble();

        assertEquals(23.05, result, 0);
    }

    @Test
    public void asDouble_returnsInfinityForExceedingValues() {
        assertEquals(Double.POSITIVE_INFINITY, new com.riguz.forks.json.JsonNumber("1e500").asDouble(), 0);
        assertEquals(Double.NEGATIVE_INFINITY, new com.riguz.forks.json.JsonNumber("-1e500").asDouble(), 0);
    }

    @Test
    public void equals_trueForSameInstance() {
        com.riguz.forks.json.JsonNumber number = new com.riguz.forks.json.JsonNumber("23");

        assertTrue(number.equals(number));
    }

    @Test
    public void equals_trueForEqualNumberStrings() {
        assertTrue(new com.riguz.forks.json.JsonNumber("23").equals(new com.riguz.forks.json.JsonNumber("23")));
    }

    @Test
    public void equals_falseForDifferentNumberStrings() {
        assertFalse(new com.riguz.forks.json.JsonNumber("23").equals(new com.riguz.forks.json.JsonNumber("42")));
        assertFalse(new com.riguz.forks.json.JsonNumber("1e+5").equals(new com.riguz.forks.json.JsonNumber("1e5")));
    }

    @Test
    public void equals_falseForNull() {
        assertFalse(new com.riguz.forks.json.JsonNumber("23").equals(null));
    }

    @Test
    public void equals_falseForSubclass() {
        assertFalse(new com.riguz.forks.json.JsonNumber("23").equals(new com.riguz.forks.json.JsonNumber("23") {
        }));
    }

    @Test
    public void hashCode_equalsForEqualStrings() {
        assertTrue(new com.riguz.forks.json.JsonNumber("23").hashCode() == new com.riguz.forks.json.JsonNumber("23")
            .hashCode());
    }

    @Test
    public void hashCode_differsForDifferentStrings() {
        assertFalse(
            new com.riguz.forks.json.JsonNumber("23").hashCode() == new com.riguz.forks.json.JsonNumber("42")
                .hashCode());
    }

    @Test
    public void canBeSerializedAndDeserialized() throws Exception {
        com.riguz.forks.json.JsonNumber number = new com.riguz.forks.json.JsonNumber("3.14");

        assertEquals(number, serializeAndDeserialize(number));
    }

}
