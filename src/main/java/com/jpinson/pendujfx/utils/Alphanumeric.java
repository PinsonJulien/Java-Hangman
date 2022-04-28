package com.jpinson.pendujfx.utils;

public abstract class Alphanumeric {

    // Methods
    public static boolean validate (String str) {
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if (!validate(c)) return false;
        }

        return true;
    }

    public static boolean validate (char c) {
        return (Character.isLetter(c) || Character.isDigit(c));
    }
}
