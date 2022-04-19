package com.jpinson.pendujfx.app.game;

import com.jpinson.pendujfx.components.healthBar.HealthBar;
import com.jpinson.pendujfx.components.keyboard.AlphabeticKeyboard;
import com.jpinson.pendujfx.components.panes.constrainedGridPane.ConstrainedGridPane;
import com.jpinson.pendujfx.components.word.Word;
import com.jpinson.pendujfx.framework.view.View;
import com.jpinson.pendujfx.utils.CssClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameView
    extends View<ConstrainedGridPane, GameViewListener>
    implements GameViewListener
{
    private final AlphabeticKeyboard keyboard = new AlphabeticKeyboard(this, true);
    private final Word word = new Word();
    private final HealthBar healthBar = new HealthBar();
    private final Button forfeitButton = new Button("FORFEIT");
    private final Label scoreLabel = new Label("SCORE");
    private final Label scoreValueLabel = new Label("0");

    public GameView() {
        super(new ConstrainedGridPane());
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
        this.pane.setId("game");

        // Set actions
        this.forfeitButton.setOnAction(this.forfeitButtonHandler);

        // Set Grid
        this.pane.setColumns(15, 70, 15);
        this.pane.setRows(70, 30);

        // Middle pane
        ConstrainedGridPane middleGrid = new ConstrainedGridPane();
        middleGrid.setColumns(100);
        middleGrid.setRows(50, 50);
        this.pane.add(middleGrid, 1, 0);

        VBox wordPane = new VBox();
        CssClass.add(wordPane, "word-pane");
        wordPane.getChildren().add(this.word);
        middleGrid.add(wordPane, 0,0);
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
    public void KeyboardPressedKey(char c) {
        for (GameViewListener listener : this.getListeners()) {
            listener.KeyboardPressedKey(c);
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


    // Methods

    public void setScoreValue (String score) {
        this.scoreValueLabel.setText(score);
    }
}
