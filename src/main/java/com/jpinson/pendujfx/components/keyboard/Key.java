package com.jpinson.pendujfx.components.keyboard;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class Key extends Button implements InitResetInterface {
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

    // Getters / Setters
    public char getValue() {
        return this.value;
    }

    // Interfaces
    @Override
    public void init() {}

    @Override
    public void reset() {}

    // Methods
}
