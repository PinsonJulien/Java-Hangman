package com.jpinson.pendujfx.utils;

import java.util.ArrayList;

public class EncryptedWord {
    private String word;
    private final ArrayList<EncryptedLetter> letters = new ArrayList<>();

    public EncryptedWord () {}

    // getters / setters
    public String getWord() {
        return this.word;
    }

    public ArrayList<EncryptedLetter> getLetters() {
        // Provides a copy.
        return new ArrayList<>(this.letters);
    }

    public void newEncryption(String word) {
        this.word = word;
        // Empty the previous values
        this.letters.clear();

        char[] chars = word.toCharArray();

        for (char c : chars) {
            EncryptedLetter letter = new EncryptedLetter(
                c,
                Alphabet.isAlpha(c)
            );

            this.letters.add(letter);
        }
    }

    public void decrypt() {
        for (EncryptedLetter letter : this.letters) {
            letter.setEncrypted(false);
        }
    }

    public void decrypt (char c) {
        if (!Alphabet.isAlpha(c)) return;

        c = Character.toUpperCase(c);

        for (EncryptedLetter letter : this.letters) {
            char value = Character.toUpperCase(letter.getValue());

            if (value == c) {
                letter.setEncrypted(false);
            }
        }
    }

    public boolean isDecrypted () {
        for (EncryptedLetter letter : this.letters) {
            if (letter.isEncrypted()) return false;
        }

        return true;
    }

    public boolean contains (char c) {
        c = Character.toUpperCase(c);
        for (EncryptedLetter letter : this.letters) {
            char value = Character.toUpperCase(letter.getValue());
            if (value == c) return true;
        }

        return false;
    }
}
