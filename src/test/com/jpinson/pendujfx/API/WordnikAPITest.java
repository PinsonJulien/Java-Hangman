package com.jpinson.pendujfx.API;

import junit.framework.TestCase;
import org.json.JSONObject;

import java.io.IOException;

public class WordnikAPITest extends TestCase {
    private final WordnikAPI api = new WordnikAPI();

    public void testFetchRandomWord() throws IOException, InterruptedException {
        JSONObject json = api.fetchRandomWord(4, 4);
        String word = json.getString("word");
        assertEquals(4, word.length());
    }

}
