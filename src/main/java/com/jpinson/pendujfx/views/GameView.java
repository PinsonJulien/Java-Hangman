package com.jpinson.pendujfx.views;
import com.jpinson.pendujfx.components.keyboard.AlphabeticKeyboard;
import com.jpinson.pendujfx.components.word.Word;
import javafx.scene.layout.Pane;

public class GameView extends View<Pane, GameViewListener> implements GameViewListener {
    private final AlphabeticKeyboard keyboard;
    private final Word word;

    public GameView() {
        super(new Pane());
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
        this.insertNode(this.keyboard, this.word);
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
