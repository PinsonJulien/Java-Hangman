package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.framework.model.Model;

public class WordModel extends Model {
    private final int id;
    private final int score;
    private final String word;

    public WordModel(
        int id,
        String word,
        int score
    ) {
        this.id = id;
        this.score = score;
        this.word = word;
    }

    public String get() {
        return word;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }
}
