package com.jpinson.pendujfx.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class GameOverView extends View<VBox, GameOverViewListener> implements GameOverViewListener {
    private final Label text = new Label("GAME OVER");
    private final Button newGameButton = new Button("NEW GAME");
    private final Button menuButton =  new Button("MENU");

    public GameOverView () {
        super(new VBox());
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.newGameButton.setOnAction(this.newGameButtonHandler);
        this.menuButton.setOnAction(this.menuButtonHandler);
        this.insertNode(this.text, this.newGameButton, this.menuButton);
    }

    @Override
    public void reset() {

    }

    // Events
    private final EventHandler<ActionEvent> newGameButtonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            NewGameButtonPressed();
        }
    };

    private final EventHandler<ActionEvent> menuButtonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            MenuButtonPressed();
        }
    };

    // Listeners
    @Override
    public void NewGameButtonPressed() {
        for (GameOverViewListener listener : getListeners()) {
            listener.NewGameButtonPressed();
        }
    }

    @Override
    public void MenuButtonPressed() {
        for (GameOverViewListener listener : getListeners()) {
            listener.MenuButtonPressed();
        }
    }

    // Methods
}
