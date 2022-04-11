package com.jpinson.pendujfx.components.formFields;

import javafx.scene.control.ComboBox;

public class ComboBoxFormField<F> extends FormField<ComboBox<F>> {
    public ComboBoxFormField (String label) {
        super(new ComboBox<F>(), label);
    }

    public F getValue() {
        return this.field.getValue();
    }

    public void setValue(F value) {
        this.field.setValue(value);
    }

    public void addValue(F value) {
        this.field.getItems().add(value);
    }
}
