package com.jpinson.pendujfx.components.difficultyComboBox;

import com.jpinson.pendujfx.enums.DifficultyEnum;
import javafx.scene.control.ComboBox;

public class DifficultyComboBox extends ComboBox<DifficultyEnum> {
    public DifficultyComboBox (DifficultyEnum defaultDifficulty) {
        this.setValue(defaultDifficulty);
        this.generate();
    }

    private void generate() {
        for (DifficultyEnum val : DifficultyEnum.values()) {
            this.getItems().add(val);
        }
    }
}
