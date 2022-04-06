package com.jpinson.pendujfx.views;
import com.jpinson.pendujfx.components.keyboard.AlphabeticKeyboard;
import com.jpinson.pendujfx.components.word.Word;

public class GameView extends ListenableView<GameViewListener> implements GameViewListener {
    private final AlphabeticKeyboard keyboard;
    private final Word word;

    public GameView() {
        this.keyboard = new AlphabeticKeyboard(this, true);
        this.word = new Word("");
        this.initComponents();
    }

    public AlphabeticKeyboard getKeyboard() {
        return keyboard;
    }

    public Word getWord() {
        return word;
    }

    public void initComponents() {
        this.addNode(this.keyboard);
        this.addNode(this.word);
    }

    // -------------------------------------------------------------------------------
    // Listeners
    // -------------------------------------------------------------------------------
    @Override
    public void KeyboardPressedKey(char c) {
        for (GameViewListener listener : this.getListeners()) {
            listener.KeyboardPressedKey(c);
        }
    }
}
