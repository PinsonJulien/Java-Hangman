package com.jpinson.pendujfx.services;

import com.jpinson.pendujfx.config.Config;
import com.jpinson.pendujfx.framework.service.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordService extends Service {

    public String getWordOnline (int length) throws IOException, InterruptedException {
        String apiKey = Config.dicolinkKey;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://wordsapiv1.p.rapidapi.com/words/hatchback/typeOf"))
                .header("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "b0713ce083msh29410592c159b75p11ca73jsn0d571b8c78fa")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());



        //String url = "https://api.dicolink.com/v1/mots/motauhasard";

        return "word";
    }

    @Override
    protected Object generateModel(ResultSet rs) throws SQLException {
        return null;
    }
}
