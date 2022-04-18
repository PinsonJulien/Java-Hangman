package com.jpinson.pendujfx.models;

import com.jpinson.pendujfx.framework.model.Model;

// Model having player related variables.
public class UserModel extends Model {
    private int id = 0;
    private String name = "";

    public UserModel() {}
    public UserModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters / Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
