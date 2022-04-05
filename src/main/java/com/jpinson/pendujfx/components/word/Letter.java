package com.jpinson.pendujfx.components.word;

import javafx.scene.control.Label;

public class Letter extends Label {
    private final Character value;
    private boolean hidden = false;
    private static final Character placeholder = '?';

    public Letter (Character c) {
        super(c.toString());
        this.value = c;
        this.init();
    }

    public Letter (Character c, boolean hidden) {
        super((hidden) ? placeholder.toString() : c.toString());
        this.value = c;
        this.hidden = hidden;
        this.init();
    }

    public Character getValue() {
        return this.value;
    }

    public boolean getHidden() {
        return this.hidden;
    }

    private void init() {
    }

    public void showCharacter () {
        this.setText(this.value.toString());
        this.hidden = false;
    }
}
