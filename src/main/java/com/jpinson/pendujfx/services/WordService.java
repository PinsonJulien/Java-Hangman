package com.jpinson.pendujfx.services;

import com.jpinson.pendujfx.API.WordnikAPI;
import com.jpinson.pendujfx.framework.service.Service;
import com.jpinson.pendujfx.models.WordModel;
import com.jpinson.pendujfx.utils.Scrabble;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WordService extends Service<WordModel> {
    private final WordnikAPI wordnikAPI = new WordnikAPI();

    @Override
    protected WordModel generateModel(ResultSet rs) throws SQLException {
        final int id = rs.getInt("word_id");
        final String word = rs.getString("word_word");
        final int score = Scrabble.getScore(word);

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

    public WordModel getRandomWordOnline (int minLength, int maxLength) throws IOException, InterruptedException {
        final JSONObject json = wordnikAPI.fetchRandomWord(minLength, maxLength);
        final String word = json.getString("word");
        final int id = json.getInt("id");
        final int score = Scrabble.getScore(word);

        return new WordModel(
            id,
            word,
            score
        );
    }
}
