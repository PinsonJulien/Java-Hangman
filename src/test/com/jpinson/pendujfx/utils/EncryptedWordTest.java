package com.jpinson.pendujfx.utils;

import junit.framework.TestCase;

import java.util.ArrayList;

public class EncryptedWordTest extends TestCase {
    private static final String word = "A+Za";
    private static final String word2 = "C?";
    EncryptedWord encryptedWord = new EncryptedWord();

    public void testNewEncryption() {
        encryptedWord.newEncryption(word);
        ArrayList<EncryptedLetter> letters = encryptedWord.getLetters();

        // It register values properly
        assertEquals('A', letters.get(0).getValue());
        assertEquals('+', letters.get(1).getValue());
        assertEquals('Z', letters.get(2).getValue());
        assertEquals('a', letters.get(3).getValue());

        // It encrypt only letters.
        assertTrue(letters.get(0).isEncrypted());
        assertFalse(letters.get(1).isEncrypted());
        assertTrue(letters.get(2).isEncrypted());
        assertTrue(letters.get(3).isEncrypted());

        // It works the same even if renewed.
        encryptedWord.newEncryption(word2);
        letters = encryptedWord.getLetters();
        assertEquals('C', letters.get(0).getValue());
        assertEquals('?', letters.get(1).getValue());
        assertTrue(letters.get(0).isEncrypted());
        assertFalse(letters.get(1).isEncrypted());
    }

    public void testDecrypt() {
        encryptedWord.newEncryption(word);
        ArrayList<EncryptedLetter> letters = encryptedWord.getLetters();

        // Decrypts regardless of case and all duplicates.
        encryptedWord.decrypt('A');
        encryptedWord.decrypt('Z');

        assertFalse(letters.get(0).isEncrypted());
        assertFalse(letters.get(1).isEncrypted());
        assertFalse(letters.get(2).isEncrypted());
        assertFalse(letters.get(3).isEncrypted());
    }

    public void testIsDecrypted() {
        encryptedWord.newEncryption(word);

        // Decrypts regardless of case and all duplicates.
        encryptedWord.decrypt('A');
        assertFalse(encryptedWord.isDecrypted());

        encryptedWord.decrypt('Z');
        assertTrue(encryptedWord.isDecrypted());
    }

    public void testContains() {
        encryptedWord.newEncryption(word);
        // Can tell if a given char exist in said word, regardless of case.
        assertTrue(encryptedWord.contains('a'));
        assertTrue(encryptedWord.contains('A'));
        assertFalse(encryptedWord.contains('b'));
        assertTrue(encryptedWord.contains('z'));
    }
}
