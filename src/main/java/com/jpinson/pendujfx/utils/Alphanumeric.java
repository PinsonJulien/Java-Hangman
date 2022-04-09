package com.jpinson.pendujfx.utils;

public abstract class Alphanumeric {

    // Methods
    public static boolean validate (String str) {
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if (!(Character.isLetter(c) || Character.isDigit(c))) return false;
        }

        return true;
    }
}
