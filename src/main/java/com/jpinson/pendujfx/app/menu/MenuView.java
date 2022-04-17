package com.jpinson.pendujfx.app.menu;

import com.jpinson.pendujfx.components.panes.constrainedGridPane.ConstrainedGridPane;
import com.jpinson.pendujfx.framework.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MenuView extends View<ConstrainedGridPane, MenuViewListener> implements MenuViewListener {
    private final VBox gameTitlePane = new VBox();
    private final Label gameTitleLabel = new Label("HANGMAN");

    private final VBox buttonPane = new VBox();
    private final Button playButton = new Button("START");
    private final Button scoresButton = new Button("SCORES");

    public MenuView() {
        super(new ConstrainedGridPane());
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        // Set actions
        this.playButton.setOnAction(this.newGameButtonHandler);
        this.scoresButton.setOnAction(this.menuButtonHandler);

        this.pane.setId("menu");

        // Set placement
        this.pane.setColumns(100);
        this.pane.setRows(50, 50);
        this.pane.add(this.gameTitlePane, 0, 0);
        this.pane.add(this.buttonPane, 0, 1);

        // Game name panel
        this.gameTitlePane.getChildren().addAll(
            this.gameTitleLabel
        );
        this.gameTitlePane.getStyleClass().add("titlePane");

        // Buttons panel
        this.buttonPane.getChildren().addAll(
            this.playButton,
            this.scoresButton
        );
        this.buttonPane.getStyleClass().add("buttonPane");

    }

    @Override
    public void reset() {}

    // Listeners

    @Override
    public void startButtonPressed() {
        for (MenuViewListener listener : getListeners()) {
            listener.startButtonPressed();
        }
    }

    @Override
    public void scoresButtonPressed() {
        for (MenuViewListener listener : getListeners()) {
            listener.scoresButtonPressed();
        }
    }

    // Events
    private final EventHandler<ActionEvent> newGameButtonHandler = actionEvent -> this.startButtonPressed();
    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> this.scoresButtonPressed();

    // Methods
}
