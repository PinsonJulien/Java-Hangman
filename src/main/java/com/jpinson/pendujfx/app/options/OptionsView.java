package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.framework.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class OptionsView extends View<VBox, OptionsViewListener> implements OptionsViewListener {
    private final Button validateButton = new Button("Validate");
    private final Button menuButton = new Button("Return to menu");

    public OptionsView() {
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
        for (OptionsViewListener listener : getListeners()) {
            listener.validateButtonPressed();
        }
    }

    @Override
    public void menuButtonPressed() {
        for (OptionsViewListener listener : getListeners()) {
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
