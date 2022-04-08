package com.jpinson.pendujfx.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OptionView extends View<VBox, OptionViewListener> implements OptionViewListener {
    private final Button validateButton = new Button("Validate");
    private final Button menuButton = new Button("Return to menu");

    public OptionView() {
        super(new VBox());
        this.init();
    }

    // Getters / Setters

    // Interfaces

    @Override
    public void init() {
        this.validateButton.setOnAction(this.validateButtonHandler);
        this.menuButton.setOnAction(this.menuButtonHandler);
        this.insertNode(this.validateButton, this.menuButton);
    }

    @Override
    public void reset() {

    }

    // Listeners

    @Override
    public void validateButtonPressed() {
        for (OptionViewListener listener : getListeners()) {
            listener.validateButtonPressed();
        }
    }

    @Override
    public void menuButtonPressed() {
        for (OptionViewListener listener : getListeners()) {
            listener.menuButtonPressed();
        }
    }

    // Events
    private final EventHandler<ActionEvent> validateButtonHandler = actionEvent -> {
        this.validateButtonPressed();
    };

    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> {
        this.menuButtonPressed();
    };

    // Methods

}
