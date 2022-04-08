package com.jpinson.pendujfx.views;
import com.jpinson.pendujfx.components.keyboard.AlphabeticKeyboard;
import com.jpinson.pendujfx.components.word.Word;
import com.jpinson.pendujfx.components.healthBar.HealthBar;
import javafx.scene.layout.VBox;

public class GameView extends View<VBox, GameViewListener> implements GameViewListener {
    private final AlphabeticKeyboard keyboard = new AlphabeticKeyboard(this, true);
    private final Word word = new Word();
    private final HealthBar healthBar = new HealthBar();

    public GameView() {
        super(new VBox());
        this.init();
    }

    // Getters / setters
    public AlphabeticKeyboard getKeyboard() {
        return keyboard;
    }
    public Word getWord() {
        return word;
    }
    public HealthBar getHealthBar() {
        return healthBar;
    }

    // Interfaces
    @Override
    public void init() {
        this.insertNode(this.keyboard, this.word, this.healthBar);
    }

    @Override
    public void reset() {
        this.keyboard.toggleAllKeys(true);
        this.healthBar.setFullHealth();
    }

    // Listeners
    @Override
    public void KeyboardPressedKey(char c) {
        for (GameViewListener listener : this.getListeners()) {
            listener.KeyboardPressedKey(c);
        }
    }

    // Methods
}
