package com.jpinson.pendujfx.utils;

import junit.framework.TestCase;

public class EncryptedWordTest extends TestCase {
    private static final char encryptingCharacter = '?';
    private static final String word = "Aujourd'hui";

    public void testEncrypt() {
        EncryptedWord encryptedWord = new EncryptedWord(word, encryptingCharacter);

        // Insert the original word properly.
        assertEquals(word, encryptedWord.getOriginal());

        // Encrypt properly the given word, but not the special characters.
        assertEquals("???????'???", encryptedWord.get());
    }

    public void testDecrypt() {
        EncryptedWord encryptedWord = new EncryptedWord(word, encryptingCharacter);
        encryptedWord.decrypt('a');
        encryptedWord.decrypt('U');

        // Decrypt regardless of case, can decrypt multiple letters.
        assertEquals("Au??u??'?u?", encryptedWord.get());
    }

    public void testIsDecrypted() {
        EncryptedWord encryptedWord = new EncryptedWord(word, encryptingCharacter);
        encryptedWord.decrypt('a');
        encryptedWord.decrypt('u');
        encryptedWord.decrypt('j');
        encryptedWord.decrypt('o');
        encryptedWord.decrypt('r');
        encryptedWord.decrypt('d');
        encryptedWord.decrypt('h');
        // Can tell if the word is completely decrypted or not.
        assertFalse(encryptedWord.isDecrypted());
        encryptedWord.decrypt('i');
        assertTrue(encryptedWord.isDecrypted());
    }

    public void testContains() {
        EncryptedWord encryptedWord = new EncryptedWord(word, encryptingCharacter);
        // Can tell if a given char exist in said word, regardless of case.
        assertTrue(encryptedWord.contains('a'));
        assertTrue(encryptedWord.contains('U'));
        assertFalse(encryptedWord.contains('z'));
    }
}
