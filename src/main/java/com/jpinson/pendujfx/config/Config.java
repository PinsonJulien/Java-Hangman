package com.jpinson.pendujfx.config;

import io.github.cdimascio.dotenv.Dotenv;

public abstract class Config {
    public static final String dicolinkKey;

    static {
        Dotenv dotenv = Dotenv.load();
        dicolinkKey = dotenv.get("DICOLINK_KEY");
    }
}
