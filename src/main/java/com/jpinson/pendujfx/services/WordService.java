package com.jpinson.pendujfx.services;

import com.jpinson.pendujfx.config.Config;
import com.jpinson.pendujfx.framework.service.Service;
import com.jpinson.pendujfx.models.WordModel;
import com.jpinson.pendujfx.utils.Scrabble;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordService extends Service<WordModel> {

    @Override
    protected WordModel generateModel(ResultSet rs) throws SQLException {
        final int id = rs.getInt("word_id");
        final String word = rs.getString("word_word");
        final int score = Scrabble.getWordScore(word);

        return new WordModel(
            id,
            word,
            score
        );
    }

    public WordModel getRandomWord (
        int minLength,
        int maxLength
    ) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("""
            SELECT
                id AS 'word_id',
                word AS 'word_word'
            FROM words
            WHERE
                LENGTH(word) BETWEEN ? AND ?
            ORDER BY RANDOM()
            LIMIT 1;
        """);

        statement.setInt(1, minLength);
        statement.setInt(2, maxLength);
        ResultSet rs = statement.executeQuery();
        rs.next();

        return this.generateModel(rs);
    }


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
}
