package com.jpinson.pendujfx.models;

public class GameModel extends Model {
    private String username;
    private int health;
    private String word;
    private String encryptedWord;

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


}
