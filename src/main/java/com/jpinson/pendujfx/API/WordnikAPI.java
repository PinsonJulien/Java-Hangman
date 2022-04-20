package com.jpinson.pendujfx.API;

import com.jpinson.pendujfx.config.Config;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WordnikAPI extends API {
    private final String key = Config.wordnikKey;

    public WordnikAPI() {
        super("https://api.wordnik.com/v4/words.json");
    }

    public JSONObject fetchRandomWord (int minLength, int maxLength) throws IOException, InterruptedException {
        URI uri = this.buildURI(
        "/randomWord",
    "minLength=" + minLength,
            "maxLength=" + maxLength,
            "api_key=" + this.key
        );

        HttpRequest request = HttpRequest.newBuilder()
        .uri(uri)
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}
