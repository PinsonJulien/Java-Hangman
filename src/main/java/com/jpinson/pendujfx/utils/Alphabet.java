package com.jpinson.pendujfx.utils;

// Generate an alphabet inside an array of char.
public abstract class Alphabet {
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
    public static final char[] array = letters;
    public static final String string = String.valueOf(letters);
    public static final int length = letters.length;

    // Methods
    public static boolean isAlpha(char c) {
        return Character.isAlphabetic(c);
    }
}
