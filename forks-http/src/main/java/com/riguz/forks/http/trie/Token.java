package com.riguz.forks.http.trie;

import com.riguz.forks.http.Rfc3986;

import static com.riguz.forks.http.Rfc3986.PATH_DELIM;
import static com.riguz.forks.http.Rfc3986.isLegalPath;

public class Token {
    public static final char WILDCARD_PATTERN = '*';
    public static final char NAMED_PATTERN = ':';

    private final char path;
    private final boolean isParameter;
    private final String parameterName;

    private Token(char path, boolean isParameter, String parameterName) {
        this.path = path;
        this.isParameter = isParameter;
        this.parameterName = parameterName;
    }

    public static Token generalPath(char path) {
        if (!isLegalPath(path))
            throw new IllegalArgumentException("Invalid general path:" + path);
        return new Token(path, false, null);
    }

    public static Token wildcard(String parameterName) {
        return new Token(WILDCARD_PATTERN, true, parameterName);
    }

    public static Token named(String parameterName) {
        return new Token(NAMED_PATTERN, true, parameterName);
    }

    public static Token feed(String pattern, int offset) {
        if (pattern == null || offset < 0 || offset >= pattern.length())
            throw new IllegalArgumentException("Index out of bound");
        final char p = pattern.charAt(offset);
        switch (p) {
            case WILDCARD_PATTERN:
                return wildcard(feedParameter(pattern, offset + 1, false));
            case NAMED_PATTERN:
                return named(feedParameter(pattern, offset + 1, true));
            default:
                if (isLegalPath(p))
                    return generalPath(p);
                else
                    throw new InvalidPatternException("Invalid path found:" + pattern + " at " + offset);
        }
    }

    public static String feedParameter(String pattern, int offset, boolean stopAtDelim) {
        StringBuilder builder = new StringBuilder();
        for (int i = offset; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (stopAtDelim && c == PATH_DELIM)
                break;
            if (!Rfc3986.isUnreserved(c))
                throw new InvalidPatternException("Parameter name contains invalid character:" + c);
            builder.append(c);
        }
        if (builder.length() == 0)
            throw new InvalidPatternException("Parameter name not specified");
        return builder.toString();
    }

    public char getPath() {
        return path;
    }

    public boolean isParameter() {
        return isParameter;
    }

    public String getParameterName() {
        return parameterName;
    }

    public int getLength() {
        if (isParameter)
            return parameterName.length() + 1;
        return 1;
    }

    @Override
    public String toString() {
        if (isParameter)
            return path + "<" + parameterName + ">";
        else return String.valueOf(path);
    }
}
