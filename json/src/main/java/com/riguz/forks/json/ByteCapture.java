package com.riguz.forks.json;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class ByteCapture {
    protected ByteOutputStream stream;

    public ByteCapture() {
        this.stream = new ByteOutputStream();
    }

    public ByteCapture(int capacity) {
        this.stream = new ByteOutputStream(capacity);
    }

    public void startCapture() {
        stream.reset();
    }

    public void capture(byte value) {
        stream.write(value);
    }

    public byte[] endCapture() {
        return stream.getBytes();
    }

    @Override
    public String toString() {
        return stream.toString();
    }

    public int toInt() {
        return Integer.parseInt(stream.toString());
    }

    public float toFloat() {
        return Float.parseFloat(stream.toString());
    }

    public double toDouble() {
        return Double.parseDouble(stream.toString());
    }
}
