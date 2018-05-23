package com.riguz.forks.json;

import java.util.Objects;

public class Location {
    protected int line;
    protected int offset;
    protected char value;

    public Location(int line, int offset, char value) {
        this.line = line;
        this.offset = offset;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public int getOffset() {
        return offset;
    }

    public char getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return line == location.line &&
                offset == location.offset &&
                value == location.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, offset, value);
    }

    @Override
    public String toString() {
        return "Location{" +
                "line=" + line +
                ", offset=" + offset +
                ", value=" + value +
                '}';
    }
}
