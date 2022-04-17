package com.jpinson.pendujfx.utils;

public abstract class Scrabble {

    public static int getWordScore(String word) {
        char[] arr = word.toCharArray();

        int score = 0;
        for (char c : arr ) {
            score += getLetterScore(c);
        }

        return score;
    }

    public static int getLetterScore(char letter) {
        switch (Character.toUpperCase(letter)) {
            case 'A', 'E', 'I', 'L', 'N', 'O', 'R', 'S', 'T', 'U' :
                return 1;
            case 'D', 'G' :
                return 2;
            case 'B', 'C', 'M', 'P' :
                return 3;
            case 'F', 'H', 'V', 'W', 'Y' :
                return 4;
            case 'K' :
                return 5;
            case 'J', 'X' :
                return 8;
            case 'Q', 'Z' :
                return 10;
            default:
                return 0;
        }
    }
}
