package com.jpinson.pendujfx.utils;

public abstract class Scrabble {

    public static int getScore(String word) {
        char[] arr = word.toCharArray();

        int score = 0;
        for (char c : arr ) {
            score += getScore(c);
        }

        return score;
    }

    public static int getScore(char letter) {
        letter = Character.toUpperCase(letter);
        return switch (letter) {
            case 'A', 'E', 'I', 'L', 'N', 'O', 'R', 'S', 'T', 'U'
                -> 1;
            case 'D', 'G'
                -> 2;
            case 'B', 'C', 'M', 'P'
                -> 3;
            case 'F', 'H', 'V', 'W', 'Y'
                -> 4;
            case 'K'
                -> 5;
            case 'J', 'X'
                -> 8;
            case 'Q', 'Z'
                -> 10;
            default
                -> 0;
        };
    }
}
