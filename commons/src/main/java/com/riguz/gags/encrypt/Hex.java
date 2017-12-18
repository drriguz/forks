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
package com.riguz.gags.encrypt;

import java.nio.charset.Charset;

/**
 * A tool to display string => hex
 * @author riguz
 *
 */
public final class Hex {
    /**
     * Build a notepad++ like hex viewer, like this:
     * 
     * Address /  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f Dump
     * 00000000| 31 32 33 34 35 36 c3 a7 c3 b1 31 32 33 34 35 36 123456çñ123456
     * 00000010| c3 a7 c3 b1 31 32 33 34 35 36 c3 a7 c3 b1 31 32 çñ123456çñ12
     * 00000020| 33 34 35 36 c3 a7 c3 b1 31 32 33 34 35 36 61 62 3456çñ123456ab
     * 
     * notice: unlike notepad++, 0x80 will not present as '.', instead a blank you will get
     * @param bytes bytes to display
     * @param charset char set used to build a string in the Dump section.
     * @return a formatted string of the viewer
     */
    public static String hexViewer(byte[] bytes, Charset charset) {
        if (bytes == null) return null;
        StringBuffer buffer = new StringBuffer();
        buffer.append("Address /");
        for (int i = 0; i < 16; i++)
            buffer.append("  " + Integer.toHexString(i));
        buffer.append(" Dump");
        for (int i = 0; i < bytes.length; i++) {
            if (i % 16 == 0) buffer.append(String.format("\n%08x| ", i));
            buffer.append(String.format("%02x ", bytes[i]));
            if ((i + 1) % 16 == 0) buffer.append(new String(bytes, i - 15, 16, charset));
        }
        int padding = 16 - bytes.length % 16;
        if (padding == 16) padding = 0;
        for (int k = 0; k < padding; k++)
            buffer.append(" _ ");
        buffer.append(new String(bytes, bytes.length - (bytes.length % 16), bytes.length % 16, charset));
        return buffer.toString();
    }
    
    /**
     * Use the default char set to display a hex viewer.
     * @param bytes bytes to display
     * @return a formatted string of the viewer.see example above.
     */
    public static String hexViewer(byte[] bytes) {
        return hexViewer(bytes, Charset.defaultCharset());
    }
}
