package com.jpinson.pendujfx.components.keyboard;
import com.jpinson.pendujfx.utils.Alphabet;
import java.util.List;

public class AlphabeticKeyboard extends Keyboard {
    public AlphabeticKeyboard (KeyboardKeyListener listener) {
        super(generateList(), listener);
    }

    private static List<Character> generateList() {
        Alphabet alphabet = new Alphabet();
        return alphabet.getCharactersList();
    }
}
