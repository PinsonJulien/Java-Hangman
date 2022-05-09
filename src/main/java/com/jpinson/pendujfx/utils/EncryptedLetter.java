package com.jpinson.pendujfx.utils;

public class EncryptedLetter {
    private final char value;
    private boolean encrypted;

    public EncryptedLetter (char value, boolean encrypted) {
        this.value = value;
        this.encrypted = encrypted;
    }

    // Getters / Setters
    public boolean isEncrypted() {
        return encrypted;
    }

    public char getValue() {
        return value;
    }
    
    public void setEncrypted (boolean encrypted) {
        this.encrypted = encrypted;
    }
}
