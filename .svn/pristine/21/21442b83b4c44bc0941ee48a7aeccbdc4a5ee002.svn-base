package com.example.serialcommdemo.util;

import org.springframework.lang.NonNull;

import java.util.Arrays;

/**
 * @author Wei Zhanwei
 * @since 2023/3/21 14:34
 **/
public class ArrayUtil {

    public static byte[] concat(@NonNull byte[] first, @NonNull byte[] second) {
        byte[] bytes = new byte[first.length + second.length];
        System.arraycopy(first, 0, bytes, 0, first.length);
        System.arraycopy(second,0, bytes, first.length, second.length);
        return bytes;
    }

    public static byte[] slice(byte[] bytes, int from, int to) {
        return Arrays.copyOfRange(bytes, from, to);
    }
}
