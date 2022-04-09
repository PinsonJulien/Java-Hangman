package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.enums.DifficultyEnum;

public interface OptionsViewListener {
    void menuButtonPressed();
    void validateButtonPressed(DifficultyEnum difficulty, String username);
}
