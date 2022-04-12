package com.jpinson.pendujfx.config;

import io.github.cdimascio.dotenv.Dotenv;

// Contains some global variables.
public abstract class Config {
    public static final String dicolinkKey;

    static {
        // Get data from the .env file
        Dotenv dotenv = Dotenv.load();
        dicolinkKey = dotenv.get("DICOLINK_KEY");
    }
}
