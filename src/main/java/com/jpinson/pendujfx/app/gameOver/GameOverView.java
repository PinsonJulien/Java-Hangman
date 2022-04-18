package com.jpinson.pendujfx.app.gameOver;

import com.jpinson.pendujfx.components.panes.constrainedGridPane.ConstrainedGridPane;
import com.jpinson.pendujfx.framework.view.View;
import com.jpinson.pendujfx.utils.CssClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameOverView
    extends View<ConstrainedGridPane, GameOverViewListener>
    implements GameOverViewListener
{
    private final Label resultLabel = new Label();
    private final Label scoreLabel = new Label();
    private final Button replayButton = new Button("REPLAY");
    private final Button menuButton =  new Button("MENU");
    private final Button scoreButton = new Button("SCORES");

    public GameOverView () {
        super(new ConstrainedGridPane());
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.pane.setId("game-over");

        // Set events
        this.replayButton.setOnAction(this.replayButtonHandler);
        this.menuButton.setOnAction(this.menuButtonHandler);
        this.scoreButton.setOnAction(this.scoreButtonHandler);

        // Setup grid
        this.pane.setColumns(100);
        this.pane.setRows(70, 30);

        // Result pane
        VBox resultPane = new VBox();
        CssClass.add(resultPane, "result-pane");
        CssClass.add(resultLabel, "result-label");
        CssClass.add(scoreLabel, "score-label");
        resultPane.getChildren().addAll(
            this.resultLabel,
            this.scoreLabel
        );
        this.pane.add(resultPane, 0, 0);

        // Button pane
        HBox buttonPane = new HBox();
        CssClass.add(buttonPane, "button-pane");
        buttonPane.getChildren().addAll(
            this.menuButton,
            this.replayButton,
            this.scoreButton
        );
        this.pane.add(buttonPane, 0, 1);
    }

    @Override
    public void reset() {}

    // Events
    private final EventHandler<ActionEvent> replayButtonHandler = actionEvent -> ReplayButtonPressed();
    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> MenuButtonPressed();
    private final EventHandler<ActionEvent> scoreButtonHandler = actionEvent -> ScoreButtonPressed();

    // Listeners
    @Override
    public void ReplayButtonPressed() {
        for (GameOverViewListener listener : getListeners()) {
            listener.ReplayButtonPressed();
        }
    }

    @Override
    public void MenuButtonPressed() {
        for (GameOverViewListener listener : getListeners()) {
            listener.MenuButtonPressed();
        }
    }

    @Override
    public void ScoreButtonPressed() {
        for (GameOverViewListener listener : getListeners()) {
            listener.ScoreButtonPressed();
        }
    }

    // Methods

    public void setWin(int score) {
        String resultText = "You won !";
        String scoreText =
                "+ " +
                score +
                " points";

        this.setResults(resultText, scoreText);
    }

    public void setLoose(int score) {
        String resultText = "You lost !";
        String scoreText =
                "- " +
                score +
                " points";

        this.setResults(resultText, scoreText);
    }

    private void setResults(String message, String score) {
        this.resultLabel.setText(message);
        this.scoreLabel.setText(score);
    }
}
