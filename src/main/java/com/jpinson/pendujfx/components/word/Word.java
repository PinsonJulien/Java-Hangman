package com.jpinson.pendujfx.components.word;

import com.jpinson.pendujfx.utils.Alphabet;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import java.util.ArrayList;

public class Word extends HBox {
    private final String word;
    private final ArrayList<Letter> letters = new ArrayList<>();

    public Word (String word) {
        this.word = word.toUpperCase();
        this.initComponents();
    }

    public String getWord() {
        return this.word;
    }

    private void initComponents() {
        ObservableList<Node> childrenList = this.getChildren();

        int len = this.word.length();
        for (int i = 0; i < len; ++i) {
            Character c = this.word.charAt(i);
            boolean isAlpha = new Alphabet().isAlphabetic(c);
            Letter letter = new Letter(c, isAlpha);
            this.letters.add(letter);

            childrenList.add(letter);
        }
    }

    public void swapLetters(Character c) {
        for (Letter letter : this.letters) {
            boolean hidden = letter.getHidden();
            Character value = letter.getValue();

            if (value == c && hidden) {
                letter.showCharacter();
            }
        }
    }

    public void swapAllLetters() {
        for (Letter letter : this.letters) {
            letter.showCharacter();
        }
    }

    public String getLettersToString() {
        String str = "";
        for (Letter letter : this.letters) {
            str = str.concat(letter.getText());
        }

        return str;
    }
}
