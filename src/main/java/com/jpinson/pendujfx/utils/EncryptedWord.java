package com.jpinson.pendujfx.utils;

public class EncryptedWord {
    private final String originalWord;
    private String encryptedWord;
    private final char encryptingCharacter;

    public EncryptedWord (String str, char c) {
        this.originalWord = str;
        this.encryptingCharacter = c;
        this.encrypt();
    }

    public String get () {
        return this.encryptedWord;
    }

    public String getOriginal () {
        return this.originalWord;
    }

    private void encrypt () {
        char[] word = this.originalWord.toCharArray();

        int len = word.length;
        for (int i = 0; i < len ; ++i) {
            if (Alphabet.isAlpha(word[i])) {
                word[i] = this.encryptingCharacter;
            }
        }

        this.encryptedWord = String.valueOf(word);
    }

    public void decrypt (char c) {
        if (!Alphabet.isAlpha(c)) return;

        c = Character.toLowerCase(c);

        String original = this.originalWord.toLowerCase();
        char[] encrypted = this.encryptedWord.toCharArray();

        int len = encrypted.length;

        for (int i = 0 ; i < len ; ++i) {
            if (original.charAt(i) == c) {
                encrypted[i] = this.originalWord.charAt(i);
            }
        }

        this.encryptedWord = String.valueOf(encrypted);
    }

    public boolean isDecrypted () {
        return this.originalWord.equals(this.encryptedWord);
    }

    public boolean contains (char c) {
        if (!Alphabet.isAlpha(c)) return false;

        c = Character.toLowerCase(c);

        return this.originalWord.toLowerCase().indexOf(c) >= 0;
    }
}
