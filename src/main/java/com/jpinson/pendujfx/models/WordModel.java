package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.framework.model.Model;

public class WordModel extends Model {
    private int id;
    private int score;
    private String word;

    public WordModel(
        int id,
        String word,
        int score
    ) {
        this.id = id;
        this.score = score;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getWord() {
        return word;
    }
}
