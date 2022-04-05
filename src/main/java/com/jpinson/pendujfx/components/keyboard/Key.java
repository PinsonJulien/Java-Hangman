package com.jpinson.pendujfx.components.keyboard;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class Key extends Button {
    private final Character value;

    public Key(Character c) {
        super("" + c);
        this.value = c;
        this.init();
    }

    public Key(Character c, Node graphic) {
        super("" + c, graphic);
        this.value = c;
        this.init();
    }

    public Character getValue() {
        return this.value;
    }

    private void init() {
        this.getStyleClass().add("test");
        this.setDisable(false);
    }

}
