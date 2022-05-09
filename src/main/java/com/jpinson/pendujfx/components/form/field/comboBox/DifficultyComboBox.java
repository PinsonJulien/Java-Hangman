package com.jpinson.pendujfx.components.form.field.comboBox;

import com.jpinson.pendujfx.enums.DifficultyEnum;

public class DifficultyComboBox extends EnumComboBox<DifficultyEnum> {
    public DifficultyComboBox (DifficultyEnum defaultDifficulty) {
        super(defaultDifficulty);
        this.generate();
    }

    private void generate() {
        for (DifficultyEnum val : DifficultyEnum.values()) {
            this.getItems().add(val);
        }
    }
}
