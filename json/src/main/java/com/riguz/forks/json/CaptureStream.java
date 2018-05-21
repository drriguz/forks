package com.riguz.forks.json;

public class CaptureStream {
    protected char[] buffer;
    protected int count;

    public CaptureStream(int capacity) {
        this.count = 0;
        this.buffer = new char[capacity];
    }

    public CaptureStream() {
        this(1024);
    }

    protected void ensureCapacity(int space) {
        int newcount = space + this.count;
        if (newcount > this.buffer.length) {
            char[] newBuffer = new char[Math.max(this.buffer.length << 1, newcount)];
            System.arraycopy(this.buffer, 0, newBuffer, 0, this.count);
            this.buffer = newBuffer;
        }
    }

    public void startCapture() {
        this.count = 0;
    }

    public void startCapture(char value) {
        this.count = 0;
        capture(value);
    }

    public void capture(char value) {
        ensureCapacity(1);
        this.buffer[count++] = value;
    }

    public char[] getBuffer() {
        return buffer;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return new String(this.buffer, 0, this.count);
    }
}
