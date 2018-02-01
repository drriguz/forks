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
package com.riguz.commons.encrypt;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Encoding tools such as base64
 *
 * notice : in RFC2045 says: The encoded output stream must be represented in lines of no more than 76 characters each
 *
 * @author riguz
 */
public final class Encoding {

    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Encode bytes to base64 string
     *
     * @param source source to encode
     * @return base 64 encoded string , or null if failed
     */
    public static String toBase64(byte[] source) {
        if (source == null) {
            return null;
        }

        return Base64.getEncoder().encodeToString(source);
    }

    /**
     * Encode bytes to base64 string, use specified charset to get bytes from string
     *
     * @param source source string to encode
     * @param charset charset to get bytes from string
     * @return base 64 encoded string , or null if failed
     */
    public static String toBase64(String source, Charset charset) {
        if (source == null) {
            return null;
        }

        return toBase64(source.getBytes(charset));
    }

    /**
     * Using default charset to encode base64 string
     *
     * @param source source string to encode
     * @return base 64 encoded string , or null if failed
     */
    public static String toBase64(String source) {
        return toBase64(source, Charset.forName(DEFAULT_ENCODING));
    }

    /**
     * Decode from base64 string using specified charset to build result
     *
     * @param str base64 encoded string
     * @param charset char set for result string
     * @return decoded string
     */
    public static String fromBase64(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return new String(Base64.getDecoder().decode(str), charset);
    }

    /**
     * Decode use default char set
     *
     * @param str base64 encoded string
     * @return decoded string
     */
    public static String fromBase64(String str) {
        return fromBase64(str, Charset.forName(DEFAULT_ENCODING));
    }
}
