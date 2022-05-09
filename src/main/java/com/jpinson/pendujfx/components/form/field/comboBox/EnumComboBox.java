package com.jpinson.pendujfx.components.form.field.comboBox;

import javafx.scene.control.ComboBox;

public abstract class EnumComboBox<E extends Enum<?>> extends ComboBox<E> {
    public EnumComboBox (E defaultValue) {
        this.setValue(defaultValue);
    }
}
