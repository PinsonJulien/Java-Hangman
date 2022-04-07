package com.jpinson.pendujfx.views;
import com.jpinson.pendujfx.components.keyboard.AlphabeticKeyboard;
import com.jpinson.pendujfx.components.word.Word;
import com.jpinson.pendujfx.components.healthBar.HealthBar;
import javafx.scene.layout.VBox;

public class GameView extends View<VBox, GameViewListener> implements GameViewListener {
    private final AlphabeticKeyboard keyboard;
    private final Word word;
    private final HealthBar healthBar;

    public GameView() {
        super(new VBox());
        this.keyboard = new AlphabeticKeyboard(this, true);
        this.word = new Word("");
        this.healthBar = new HealthBar();
        this.init();
    }

    public AlphabeticKeyboard getKeyboard() {
        return keyboard;
    }
    public Word getWord() {
        return word;
    }
    public HealthBar getHealthBar() {
        return healthBar;
    }

    @Override
    public void init() {
        this.insertNode(this.keyboard, this.word, this.healthBar);
    }

    @Override
    public void reset() {
        this.keyboard.toggleAllKeys(true);
        this.healthBar.setFullHealth();
    }

    public void gameOver() {
        this.keyboard.toggleAllKeys(false);
        this.healthBar.setHealth(0);
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
