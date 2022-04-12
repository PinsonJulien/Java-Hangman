package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.framework.model.Model;

// Model having player related variables.
public class PlayerModel extends Model {
    private String name;

    // Getters / Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
