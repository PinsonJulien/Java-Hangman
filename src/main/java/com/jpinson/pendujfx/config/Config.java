package com.jpinson.pendujfx.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private final String dicolinkKey;

    public Config() {
        Dotenv dotenv = Dotenv.load();
        this.dicolinkKey = dotenv.get("DICOLINK_KEY");
    }

    public String getDicolinkKey() {
        return dicolinkKey;
    }
}
