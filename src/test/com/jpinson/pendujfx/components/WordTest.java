package com.jpinson.pendujfx.components;

import com.jpinson.pendujfx.components.word.Letter;
import com.jpinson.pendujfx.components.word.Word;
import com.jpinson.pendujfx.utils.CssClass;
import com.jpinson.pendujfx.utils.EncryptedWord;
import javafx.application.Platform;
import junit.framework.TestCase;

import java.util.ArrayList;

public class WordTest extends TestCase {

    static {
        // Allow test to work.
        Platform.startup(() -> {});
    }

    private final EncryptedWord encryptedWord = new EncryptedWord();
    private final Word word = new Word();

    public void testNewWord() {
        this.encryptedWord.newEncryption("BONJOUR");
        this.word.newWord(this.encryptedWord.getLetters());

        ArrayList<Letter> list = word.getLetters();

        assertEquals(7, list.size());

        this.encryptedWord.newEncryption("HELLO");
        this.word.newWord(this.encryptedWord.getLetters());
        list = word.getLetters();

        assertEquals(5, list.size());

    }

    public void testUpdate() {
        this.encryptedWord.newEncryption("ABA");
        this.word.newWord(this.encryptedWord.getLetters());
        this.encryptedWord.decrypt('A');
        this.word.update(this.encryptedWord.getLetters());
        ArrayList<Letter> list = word.getLetters();

        final String hiddenCls = "hidden";
        assertFalse(CssClass.contains(list.get(0), hiddenCls));
        assertTrue(CssClass.contains(list.get(1), hiddenCls));
        assertFalse(CssClass.contains(list.get(2), hiddenCls));

        this.encryptedWord.decrypt('B');
        this.word.update(this.encryptedWord.getLetters());
        assertFalse(CssClass.contains(list.get(1), hiddenCls));
    }
}
