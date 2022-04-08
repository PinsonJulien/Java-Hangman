package com.jpinson.pendujfx.components.word;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import javafx.scene.control.Label;

public class Word extends Label implements InitResetInterface {
    public Word () {
        this.set("");
        this.init();
    }

    public Word (String word) {
        this.set(word);
        this.init();
    }

    // Getters / Setters

    // Interface
    @Override
    public void init() {}

    @Override
    public void reset() {

    }

    // Methods
    public void set(String word) {
        this.setText(word);
    }
}
