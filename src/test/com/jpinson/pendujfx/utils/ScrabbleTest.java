package com.jpinson.pendujfx.utils;

import junit.framework.TestCase;

public class ScrabbleTest extends TestCase {

    public void testGetWordScore() {
        int score = Scrabble.getWordScore("ADBFKJQ-");

        assertEquals(
            1 + 2 + 3 + 4 + 5 + 8 + 10,
            score
        );

    }
}
