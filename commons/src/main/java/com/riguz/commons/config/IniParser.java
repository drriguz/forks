package com.riguz.commons.config;

import com.riguz.commons.io.BufferedParser;

import java.io.IOException;

public class IniParser extends BufferedParser {

    public IniParser() {
        super(1024);
    }

    @Override
    protected void readValue() throws IOException {
        switch (this.currentValue) {
            case '[':
                this.readSection();
                break;
            case ';':
                this.readComment();
                break;
            default:
                this.readSection();
        }
    }

    protected void readSection() throws IOException {
        System.out.println("section");
        this.read();
        this.startCapture();
        while (this.currentValue != ']') {
            this.read();
        }
        String sectionName = this.endCapture();
        System.out.println("section:" + sectionName);
    }

    protected void readComment() throws IOException {
        System.out.println("comment");
    }

    protected void readProperty() throws IOException {
        System.out.println("k-v");
    }
}
