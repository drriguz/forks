package com.riguz.forks.http;

public class Rfc3986 {
    /*
      reserved    = gen-delims / sub-delims
      gen-delims  = ":" / "/" / "?" / "#" / "[" / "]" / "@"
      sub-delims  = "!" / "$" / "&" / "'" / "(" / ")"
                  / "*" / "+" / "," / ";" / "="
      unreserved  = ALPHA / DIGIT / "-" / "." / "_" / "~"
     */
    private static final String genDelims = ":/?#[]@";
    private static final String subDelims = "!$&'()*+,;=";

    public static final char PATH_DELIM = '/';

    private static final boolean[] unreservedMapping;

    static {
        unreservedMapping = new boolean[255];
        for (char i = 'a'; i <= 'z'; i++)
            unreservedMapping[i] = true;
        for (char i = 'A'; i <= 'Z'; i++)
            unreservedMapping[i] = true;
        for (char i = '0'; i <= '9'; i++)
            unreservedMapping[i] = true;
        unreservedMapping['-'] = true;
        unreservedMapping['.'] = true;
        unreservedMapping['_'] = true;
        unreservedMapping['~'] = true;
    }

    public static boolean isUnreserved(char key) {
        return key < 255 && unreservedMapping[key];
    }

    public static boolean isLegalPath(char p) {
        return Rfc3986.isUnreserved(p) || p == PATH_DELIM;
    }
}
