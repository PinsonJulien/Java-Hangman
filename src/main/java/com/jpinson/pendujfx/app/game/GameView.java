package com.jpinson.pendujfx.app.game;

import com.jpinson.pendujfx.components.healthBar.HealthBar;
import com.jpinson.pendujfx.components.keyboard.AlphabeticKeyboard;
import com.jpinson.pendujfx.components.panes.constrainedGridPane.ConstrainedGridPane;
import com.jpinson.pendujfx.components.word.Word;
import com.jpinson.pendujfx.framework.view.View;
import com.jpinson.pendujfx.utils.Alphabet;
import com.jpinson.pendujfx.utils.CssClass;
import com.jpinson.pendujfx.utils.EncryptedLetter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GameView
    extends View<ConstrainedGridPane, GameViewListener>
    implements GameViewListener
{
    private final AlphabeticKeyboard keyboard = new AlphabeticKeyboard(this);
    private final Word word = new Word();
    private final HealthBar healthBar = new HealthBar();
    private final Button forfeitButton = new Button("FORFEIT");
    private final Label scoreLabel = new Label("SCORE");
    private final Label scoreValueLabel = new Label("0");

    public GameView() {
        super(new ConstrainedGridPane());
        this.init();
    }

    // Interfaces
    @Override
    public void init() {
        this.pane.setId("game");

        // Set actions
        this.pane.setOnKeyReleased(this.keyReleasedHandler);
        this.forfeitButton.setOnAction(this.forfeitButtonHandler);

        // Set Grid
        this.pane.setColumns(15, 70, 15);
        this.pane.setRows(70, 30);

        // Middle pane
        ConstrainedGridPane middleGrid = new ConstrainedGridPane();
        middleGrid.setColumns(100);
        middleGrid.setRows(50, 50);
        this.pane.add(middleGrid, 1, 0);

        CssClass.add(this.word, "word-pane");
        middleGrid.add(this.word, 0,0);
        middleGrid.add(this.keyboard, 0, 1);

        // Left pane
        VBox leftPane = new VBox();
        CssClass.add(leftPane, "left-pane");
        leftPane.getChildren().addAll(
            this.scoreLabel,
            this.scoreValueLabel
        );
        this.pane.add(leftPane, 0, 0);

        VBox rightPane = new VBox();
        CssClass.add(rightPane, "right-pane");
        rightPane.getChildren().add(this.healthBar);
        this.pane.add(rightPane, 2, 0);

        VBox buttonPane = new VBox();
        CssClass.add(buttonPane, "button-pane");
        buttonPane.getChildren().add(this.forfeitButton);
        this.pane.add(buttonPane, 1, 1);
    }

    @Override
    public void reset() {
        this.keyboard.toggleAllKeys(true);
        this.healthBar.setFullHealth();
    }

    // Listeners
    @Override
    public void keyboardPressedKey(char c) {
        // Disable key
        this.keyboard.toggleKey(c, false);

        for (GameViewListener listener : this.getListeners()) {
            listener.keyboardPressedKey(c);
        }
    }

    @Override
    public void forfeitButtonPressed() {
        for (GameViewListener listener : getListeners()) {
            listener.forfeitButtonPressed();
        }
    }

    // Events
    private final EventHandler<ActionEvent> forfeitButtonHandler = actionEvent -> this.forfeitButtonPressed();
    private final EventHandler<KeyEvent> keyReleasedHandler = keyEvent -> {
        final String key = keyEvent.getCode().toString();

        // Must be a single letter.
        if (key.length() > 1) return;

        final char c = key.charAt(0);

        if (!Alphabet.isAlpha(c)) return;

        // Must not have been used yet.
        if (this.keyboard.isKeyDisabled(c)) return;

        // Disable key
        this.keyboard.toggleKey(c, false);
        // trigger listener
        this.keyboardPressedKey(c);
    };

    // Methods

    public void setScoreValue (String score) {
        this.scoreValueLabel.setText(score);
    }

    public void setWord(ArrayList<EncryptedLetter> letters) {
        this.word.newWord(letters);
    }

    public void updateWord(ArrayList<EncryptedLetter> letters) {
        this.word.update(letters);
    }

    public void setHealth(double percentage) {
        this.healthBar.setHealth(percentage);
    }

    public void setFullHealth() {
        this.healthBar.setFullHealth();
    }
}
