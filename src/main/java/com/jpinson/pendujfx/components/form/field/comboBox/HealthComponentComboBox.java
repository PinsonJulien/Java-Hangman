package com.jpinson.pendujfx.components.form.field.comboBox;

import com.jpinson.pendujfx.enums.HealthComponentEnum;

public class HealthComponentComboBox extends EnumComboBox<HealthComponentEnum> {
    public HealthComponentComboBox (HealthComponentEnum defaultComponent) {
        super(defaultComponent);
        this.generate();
    }

    private void generate() {
        for (HealthComponentEnum val : HealthComponentEnum.values()) {
            this.getItems().add(val);
        }
    }
}
