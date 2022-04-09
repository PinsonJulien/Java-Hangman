package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.framework.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class OptionsView extends View<VBox, OptionsViewListener> implements OptionsViewListener {
    private final Button validateButton = new Button("Validate");
    private final Button menuButton = new Button("Return to menu");
    private final ComboBox<DifficultyEnum> difficultySelect = new ComboBox<>();
    private final Label usernameLabel = new Label("Username:");
    private final TextField usernameTextField = new TextField();
    private final Label errorLabel = new Label();

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

        this.errorLabel.setStyle("-fx-text-fill: red");

        for (DifficultyEnum val: DifficultyEnum.values()) {
            this.difficultySelect.getItems().add(val);
        }

        this.difficultySelect.setValue(DifficultyEnum.EASY);

        this.insertNode(
            this.validateButton,
            this.menuButton,
            this.difficultySelect,
            this.usernameLabel,
            this.usernameTextField,
            this.errorLabel
        );
    }

    @Override
    public void reset() {

    }

    // Listeners

    @Override
    public void validateButtonPressed(DifficultyEnum difficulty, String username) {
        for (OptionsViewListener listener : getListeners()) {
            listener.validateButtonPressed(difficulty, username);
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
        this.errorLabel.setVisible(false);

        this.validateButtonPressed(
            this.difficultySelect.getValue(),
            this.usernameTextField.getText()
        );
    };

    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> this.menuButtonPressed();

    // Methods
    public void setError(String message) {
        this.errorLabel.setText(message);
        this.errorLabel.setVisible(true);
    }
}
