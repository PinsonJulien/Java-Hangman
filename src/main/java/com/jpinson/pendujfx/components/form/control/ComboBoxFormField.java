package com.jpinson.pendujfx.components.form.control;

import javafx.scene.control.ComboBox;

public class ComboBoxFormField<
    F extends ComboBox<?>
> extends FormField<F> {
    public ComboBoxFormField (F element, String label) {
        super(element, label);
    }

    public F getField() {
        return this.field;
    }
}
