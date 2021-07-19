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

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A hash class for common hash encryption. the md5() function is totally equivalent to php md5(). you can use online
 * tools to compare the result.
 *
 * @author riguz
 */
public final class Hashs {

    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA224 = "SHA-224";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";

    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Encrypt a plain string use a hash method.
     *
     * @param method MD5 or SHA-x series
     * @param encoding encoding to get bytes of the source. eg.GBK, UTF-8
     * @param plainText the source to encrypt
     * @return encrypted string if success, or null if failed
     */
    public static String encrypt(String method, String encoding, String plainText) {
        if (plainText == null || "".equals(plainText)) {
            return null;
        }
        try {
            byte[] bytes = plainText.getBytes(encoding);
            return encrypt(method, bytes);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    /**
     * Encrypt bytes by a hash method
     *
     * @param method encoding to get bytes of the source. eg.GBK, UTF-8
     * @param bytes source to encrypt
     * @return encrypted string if success, or null if failed
     */
    public static String encrypt(String method, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            MessageDigest m = MessageDigest.getInstance(method);
            m.update(bytes, 0, bytes.length);
            return String.format("%032x", new BigInteger(1, m.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5 32 byte hash md5(hello,32) = 5d41402abc4b2a76b9719d911017c592
     *
     * @param plainText source to encrypt
     * @return encrypted string, or null if failed
     */
    public static String md5(String plainText) {
        return encrypt(MD5, DEFAULT_ENCODING, plainText);
    }

    /**
     * MD5 16 byte hash. it's the middle part of MD5-32 byte result md5(hello,16) = bc4b2a76b9719d91
     *
     * @param plainText source to encrypt
     * @return encrypted string, or null if failed
     */
    public static String md5_16(String plainText) {
        String md5 = encrypt(MD5, DEFAULT_ENCODING, plainText);
        if (md5 == null) {
            return null;
        }
        return md5.substring(8, 24);
    }

    /**
     * SHA-1 hash
     *
     * @param plainText source to encrypt
     * @return encrypted string, or null if failed
     */
    public static String sha1(String plainText) {
        return encrypt(SHA1, DEFAULT_ENCODING, plainText);
    }

    /**
     * SHA-224 hash
     *
     * @param plainText source to encrypt
     * @return encrypted string, or null if failed
     */
    public static String sha224(String plainText) {
        return encrypt(SHA224, DEFAULT_ENCODING, plainText);
    }

    /**
     * SHA-256 hash
     *
     * @param plainText source to encrypt
     * @return encrypted string, or null if failed
     */
    public static String sha256(String plainText) {
        return encrypt(SHA256, DEFAULT_ENCODING, plainText);
    }

    /**
     * SHA-384 hash
     *
     * @param plainText source to encrypt
     * @return encrypted string, or null if failed
     */
    public static String sha384(String plainText) {
        return encrypt(SHA384, DEFAULT_ENCODING, plainText);
    }

    /**
     * SHA-512 hash
     *
     * @param plainText source to encrypt
     * @return encrypted string, or null if failed
     */
    public static String sha512(String plainText) {
        return encrypt(SHA512, DEFAULT_ENCODING, plainText);
    }
}
