package com.jpinson.pendujfx.utils;

import junit.framework.TestCase;

public class ScrabbleTest extends TestCase {

    public void testGetScore() {
        assertEquals(1,  Scrabble.getScore('A'));
        assertEquals(2,  Scrabble.getScore('d'));
        assertEquals(0,  Scrabble.getScore('+'));
    }

    public void testGetScoreString() {
        assertEquals(
                1 + 2 + 3 + 4 + 5 + 8 + 10,
                Scrabble.getScore("ADBFKJQ-")
        );

        assertEquals(
                10 + 2*2 + 4*3 + 5*4 + 5 + 2*8 + 2*10,
                Scrabble.getScore("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
        );

    }
}
