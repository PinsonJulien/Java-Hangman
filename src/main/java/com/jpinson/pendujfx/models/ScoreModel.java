package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.framework.model.Model;

public class ScoreModel extends Model {
    private final int id;
    private final UserModel user;
    private final DifficultyEnum difficulty;
    private final int score;

    public ScoreModel (
        int id,
        int score,
        DifficultyEnum difficulty,
        UserModel user
    ) {
        this.id = id;
        this.score = score;
        this.difficulty = difficulty;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

    public UserModel getUser() {
        return user;
    }
}