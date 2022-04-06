package com.jpinson.pendujfx.components.keyboard;
import com.jpinson.pendujfx.utils.Alphabet;

public class AlphabeticKeyboard extends Keyboard {
    public AlphabeticKeyboard (KeyboardKeyListener listener) {
        super(listener, Alphabet.array);
    }

    public AlphabeticKeyboard (KeyboardKeyListener listener, boolean disableKeyOnUse) {
        super(listener, Alphabet.array, disableKeyOnUse);
    }
}
