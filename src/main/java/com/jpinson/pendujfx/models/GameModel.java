package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.framework.model.Model;
import com.jpinson.pendujfx.utils.EncryptedWord;

// Model having game related variables
public class GameModel extends Model {
    private final OptionsModel options;
    private WordModel word;
    private UserModel user;

    private int maxHealth;
    private int health;
    private EncryptedWord encryptedWord;
    // Determine if the game is win / loose
    private boolean status;

    public GameModel (OptionsModel options) {
        this.options = options;
    }

    // Getters / Setters
    public OptionsModel getOptions() {
        return options;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public WordModel getWord() {
        return word;
    }

    public void setWord(WordModel word) {
        this.word = word;
    }

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

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
}
