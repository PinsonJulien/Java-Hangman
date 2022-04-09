package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.framework.model.Model;

public class GameModel extends Model {
    private int health;
    private String word;
    private String encryptedWord;

    // Getters / Setters
    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getEncryptedWord() {
        return this.encryptedWord;
    }

    public void setEncryptedWord(String encryptedWord) {
        this.encryptedWord = encryptedWord;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
