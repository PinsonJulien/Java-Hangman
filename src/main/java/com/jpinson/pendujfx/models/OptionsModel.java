package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.framework.model.Model;

// Model having options of the game
public class OptionsModel extends Model {
    private DifficultyEnum difficulty;

    // Getters / Setters
    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }
}