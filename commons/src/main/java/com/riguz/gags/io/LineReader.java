/*******************************************************************************
 * Copyright (c) 2016 Riguz.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.riguz.gags.io;

import static com.riguz.gags.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;

public class LineReader {
    private final Readable   readable;
    private final Reader     reader;
    private final char[]     buf  = new char[0x1000];    // 4K
    private final CharBuffer cbuf = CharBuffer.wrap(buf);

    private final Queue<String> lines   = new LinkedList<String>();
    private final LineBuffer    lineBuf = new LineBuffer() {
                                            @Override
                                            protected void handleLine(String line, String end) {
                                                lines.add(line);
                                            }
                                        };

    public LineReader(Readable readable) {
        this.readable = checkNotNull(readable);
        this.reader = (readable instanceof Reader) ? (Reader) readable : null;
    }

    public String readLine() throws IOException {
        while (lines.peek() == null) {
            cbuf.clear();
            // The default implementation of Reader#read(CharBuffer) allocates a
            // temporary char[], so we call Reader#read(char[], int, int) instead.
            int read = (reader != null) ? reader.read(buf, 0, buf.length) : readable.read(cbuf);
            if (read == -1) {
                lineBuf.finish();
                break;
            }
            lineBuf.add(buf, 0, read);
        }
        return lines.poll();
    }
}
