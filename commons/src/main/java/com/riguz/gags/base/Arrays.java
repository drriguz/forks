package com.riguz.gags.base;

public final class Arrays {

    private Arrays() {

    }

    public static <T> T[] concat(final T[] first, final T[]... rest) {
        int length = first.length;
        for (T[] arr : rest) {
            length += arr.length;
        }
        T[] result = java.util.Arrays.copyOf(first, length);
        int offset = first.length;
        for (T[] arr : rest) {
            System.arraycopy(arr, 0, result, offset, arr.length);
            offset += arr.length;
        }
        return result;
    }

}
