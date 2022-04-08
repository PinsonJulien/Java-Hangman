package com.jpinson.pendujfx.utils;

public class Alphabet {
    private static final char[] letters;

    static {
        char start = 'A';
        char end = 'Z';
        letters = new char[end-start+1];

        for (char c = start; c <= end; ++c) {
            letters[c-start] = c;
        }
    }

    // Static variables
    public static char[] array = letters;
    public static String string = String.valueOf(letters);

    // Methods
    public static boolean isAlpha(char c) {
        return Character.isAlphabetic(c);
    }
}
