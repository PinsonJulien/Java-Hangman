package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.framework.model.Model;
import com.jpinson.pendujfx.utils.EncryptedWord;

// Model having game related variables
public class GameModel extends Model {
    private int health;
    private EncryptedWord encryptedWord;
    private int score;

    // Getters / Setters
    public int getHealth() {
        return this.health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public void setEncryptedWord(EncryptedWord encryptedWord) {
        this.encryptedWord = encryptedWord;
    }

    public EncryptedWord getEncryptedWord () {
        return this.encryptedWord;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
