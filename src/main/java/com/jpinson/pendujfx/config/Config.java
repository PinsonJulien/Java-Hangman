package com.jpinson.pendujfx.config;

import io.github.cdimascio.dotenv.Dotenv;

// Contains some global variables.
public abstract class Config {
    public static final String wordnikKey;

    static {
        // Get data from the .env file
        Dotenv dotenv = Dotenv.load();
        wordnikKey = dotenv.get("WORDNIK_KEY");
    }
}
