package com.jpinson.pendujfx.components.word;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import com.jpinson.pendujfx.utils.EncryptedLetter;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

// Holds the word of the game
public class Word extends TilePane implements InitResetInterface {
    ArrayList<Letter> letters = new ArrayList<>();

    public Word () {
        this.init();
    }

    // Getters / Setters
    public ArrayList<Letter> getLetters () {
        return this.letters;
    }

    // Interface
    @Override
    public void init() {}

    @Override
    public void reset() {

    }

    // Methods
    public void newWord(ArrayList<EncryptedLetter> items) {
        this.letters.clear();
        this.getChildren().clear();

        for (EncryptedLetter item : items) {
            final char value = item.getValue();
            final Letter letter = new Letter(value);
            this.update(letter, item);

            this.letters.add(letter);
            this.getChildren().add(letter);
        }
    }

    public void update(Letter letter, EncryptedLetter encryptedLetter) {
        final boolean encrypted = encryptedLetter.isEncrypted();
        letter.setTextVisibility(!encrypted);
    }

    public void update(ArrayList<EncryptedLetter> items) {
        final int len = items.size();
        if (len != this.letters.size()) return;

        for (int i = 0 ; i < len ; ++i) {
            this.update(
                this.letters.get(i),
                items.get(i)
            );
        }
    }
}
