package com.jpinson.pendujfx.components.form.control;

import javafx.scene.control.TextField;

public class TextFormField extends FormField<TextField> {
    public TextFormField (String label) {
        super(new TextField(), label);
    }

    public String getText() {
        return this.field.getText();
    }

    public void setText(String text) {
        this.field.setText(text);
    }
}
