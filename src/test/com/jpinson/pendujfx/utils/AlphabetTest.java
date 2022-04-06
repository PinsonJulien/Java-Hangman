package com.jpinson.pendujfx.utils;

import junit.framework.TestCase;

public class AlphabetTest extends TestCase {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public void testArray() {
        assertEquals(alphabet, String.valueOf(Alphabet.array));
    }

    public void testString() {
        assertEquals(alphabet, Alphabet.string );
    }

    public void testIsAlpha() {
        assertTrue(Alphabet.isAlpha('A'));
        assertTrue(Alphabet.isAlpha('a'));
        assertFalse(Alphabet.isAlpha('1'));
        assertFalse(Alphabet.isAlpha('$'));
    }
}
