package com.example.serialcommdemo.util;

/**
 * @author Wei Zhanwei
 * @since 2023/2/26 16:04
 **/
public class StringUtil {

    public static boolean isBlank(CharSequence cs) {
        if (cs == null) {
            return true;
        }
        int strLen = cs.length();
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
