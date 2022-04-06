package com.jpinson.pendujfx.components.keyboard;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class Key extends Button {
    private final char value;

    public Key(char c) {
        super(String.valueOf(c));
        this.value = c;
        this.init();
    }

    public Key(char c, Node graphic) {
        super(String.valueOf(c), graphic);
        this.value = c;
        this.init();
    }

    public char getValue() {
        return this.value;
    }

    private void init() {
        this.getStyleClass().add("test");
        this.setDisable(false);
    }
}
