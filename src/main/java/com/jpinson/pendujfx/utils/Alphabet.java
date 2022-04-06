package com.jpinson.pendujfx.utils;

public class Alphabet { ;
    private static char[] letters;

    static {
        char start = 'A';
        char end = 'Z';
        letters = new char[end-start+1];

        for (char c = start; c <= end; ++c) {
            letters[c-start] = c;
        }
    }

    public static char[] array = letters;
    public static String string = String.valueOf(letters);

    public static boolean isAlpha(char c) {
        return Character.isAlphabetic(c);
    }
}
