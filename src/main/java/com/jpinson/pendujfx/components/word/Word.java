package com.jpinson.pendujfx.components.word;

import javafx.scene.control.Label;

public class Word extends Label {
    public Word (String word) {
        this.set(word);
        this.init();
    }

    public void init() {}

    public void set(String word) {
        this.setText(word);
    }
}
