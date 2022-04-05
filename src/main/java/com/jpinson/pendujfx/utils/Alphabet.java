package com.jpinson.pendujfx.utils;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {
    private final List<Character> charactersList = new ArrayList<>();

    public Alphabet() {
        this.buildList();
    }

    public List<Character> getCharactersList() {
        return charactersList;
    }

    private void buildList() {
        for (char c = 'A'; c <= 'Z'; ++c) {
            this.charactersList.add(c);
        }
    }

    public boolean isAlphabetic(Character c) {
        c = Character.toUpperCase(c);
        return this.charactersList.contains(c);
    }
}
