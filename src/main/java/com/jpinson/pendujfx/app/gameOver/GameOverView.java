package com.jpinson.pendujfx.app.gameOver;

import com.jpinson.pendujfx.framework.view.View;
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
    private final EventHandler<ActionEvent> newGameButtonHandler = actionEvent -> {
        NewGameButtonPressed();
    };

    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> {
        MenuButtonPressed();
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
