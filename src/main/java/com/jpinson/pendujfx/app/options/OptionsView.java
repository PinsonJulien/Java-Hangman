package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.components.formFields.ComboBoxFormField;
import com.jpinson.pendujfx.components.formFields.TextFormField;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.framework.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class OptionsView extends View<VBox, OptionsViewListener> implements OptionsViewListener {
    private final Button validateButton = new Button("Validate");
    private final Button menuButton = new Button("Return to menu");

    private final TextFormField usernameField = new TextFormField("Username");
    private ComboBoxFormField<DifficultyEnum> difficultyField = new ComboBoxFormField<>("Difficulty");

    public OptionsView() {
        super(new VBox());
        this.init();
    }

    // Getters / Setters
    public TextFormField getUsernameField() {
        return usernameField;
    }

    public ComboBoxFormField<DifficultyEnum> getDifficultyField() {
        return difficultyField;
    }

    // Interfaces
    @Override
    public void init() {
        this.validateButton.setOnAction(this.validateButtonHandler);
        this.menuButton.setOnAction(this.menuButtonHandler);

        for (DifficultyEnum val: DifficultyEnum.values()) {
            this.difficultyField.addValue(val);
        }

        this.difficultyField.setValue(DifficultyEnum.EASY);

        this.insertNode(
            this.validateButton,
            this.menuButton,
            this.usernameField,
            this.difficultyField
        );
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
    private final EventHandler<ActionEvent> validateButtonHandler = actionEvent -> this.validateButtonPressed();
    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> this.menuButtonPressed();

    // Methods
}
