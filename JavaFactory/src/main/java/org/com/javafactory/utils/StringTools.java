package org.com.javafactory.utils;

public class StringTools {
    public static String upperCaseFirstLetter(String str) {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String lowerCaseFirstLetter(String str) {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
