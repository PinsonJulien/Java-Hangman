package com.jpinson.pendujfx.app.menu;

import com.jpinson.pendujfx.framework.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuView extends View<VBox, MenuViewListener> implements MenuViewListener {
    // Sert de hub aux options etc.

    // sélection de difficulté
    // internet (afficher si oui ou non connecté), possibilité de désactivé

    // tableau des scores (autre vue)

    private final Button playButton = new Button("Play");
    private final Button optionButton = new Button("Options");

    public MenuView() {
        super(new VBox());
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.playButton.setOnAction(this.newGameButtonHandler);
        this.optionButton.setOnAction(this.menuButtonHandler);
        this.insertNode(this.playButton, this.optionButton);
    }

    @Override
    public void reset() {}

    // Listeners

    @Override
    public void playButtonPressed() {
        for (MenuViewListener listener : getListeners()) {
            listener.playButtonPressed();
        }
    }

    @Override
    public void optionButtonPressed() {
        for (MenuViewListener listener : getListeners()) {
            listener.optionButtonPressed();
        }
    }

    // Events
    private final EventHandler<ActionEvent> newGameButtonHandler = actionEvent -> {
        this.playButtonPressed();
    };

    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> {
        this.optionButtonPressed();
    };

    // Methods
}
