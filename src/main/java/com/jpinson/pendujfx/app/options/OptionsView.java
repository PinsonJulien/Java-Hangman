package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.components.difficultyComboBox.DifficultyComboBox;
import com.jpinson.pendujfx.components.formFields.ComboBoxFormField;
import com.jpinson.pendujfx.components.formFields.TextFormField;
import com.jpinson.pendujfx.components.panes.constrainedGridPane.ConstrainedGridPane;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.framework.view.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OptionsView extends View<ConstrainedGridPane, OptionsViewListener> implements OptionsViewListener {
    private final Button playButton = new Button("PLAY");
    private final Button returnButton = new Button("RETURN");
    private final Label titleLabel = new Label("OPTIONS");
    private final TextFormField usernameField = new TextFormField("Username");
    private final ComboBoxFormField<DifficultyComboBox> difficultyField
        = new ComboBoxFormField<>(new DifficultyComboBox(DifficultyEnum.EASY), "Difficulty");

    public OptionsView() {
        super(new ConstrainedGridPane());
        this.init();
    }

    // Getters / Setters
    public TextFormField getUsernameField() {
        return usernameField;
    }

    public ComboBoxFormField<DifficultyComboBox> getDifficultyField() {
        return difficultyField;
    }

    // Interfaces
    @Override
    public void init() {
        // set actions
        this.playButton.setOnAction(this.playButtonHandler);
        this.returnButton.setOnAction(this.returnButtonHandler);
        this.pane.setId("options");

        // Set placement
        this.pane.setColumns(100);
        this.pane.setRows(20, 60, 20);

        // Title grid
        ConstrainedGridPane titleGrid = new ConstrainedGridPane();
        titleGrid.getStyleClass().add("title-grid");
        titleGrid.setColumns(15, 70, 15);
        titleGrid.setRows(100);

        VBox titleButtonPane = new VBox();
        titleButtonPane.getStyleClass().add("title-button-pane");
        titleButtonPane.getChildren().add(this.returnButton);

        VBox titleLabelPane = new VBox();
        titleLabelPane.getStyleClass().add("title-label-pane");
        titleLabelPane.getChildren().add(this.titleLabel);

        titleGrid.add(titleButtonPane, 0,0);
        titleGrid.add(titleLabelPane, 1, 0);
        this.pane.add(titleGrid, 0, 0);

        // Form grid

        ConstrainedGridPane fieldsGrid = new ConstrainedGridPane();
        fieldsGrid.setColumns(50, 50);
        fieldsGrid.setRows(100);
        fieldsGrid.add(this.usernameField, 0, 0);
        fieldsGrid.add(this.difficultyField, 1, 0);
        this.pane.add(fieldsGrid, 0, 1);

        fieldsGrid.getStyleClass().add("field-pane");
        this.usernameField.getStyleClass().add("field");
        this.difficultyField.getStyleClass().add("field");

        // Bottom grid
        VBox bottomButtonPane = new VBox();
        bottomButtonPane.getChildren().add(this.playButton);
        this.pane.add(bottomButtonPane, 0, 2);
        bottomButtonPane.getStyleClass().add("bottom-button-pane");
        this.playButton.getStyleClass().add("play-button");
    }

    @Override
    public void reset() {
        this.difficultyField.setNeutral();
        this.usernameField.setNeutral();
    }

    // Listeners

    @Override
    public void playButtonPressed() {
        for (OptionsViewListener listener : getListeners()) {
            listener.playButtonPressed();
        }
    }

    @Override
    public void returnButtonPressed() {
        for (OptionsViewListener listener : getListeners()) {
            listener.returnButtonPressed();
        }
    }

    // Events
    private final EventHandler<ActionEvent> playButtonHandler = actionEvent -> this.playButtonPressed();
    private final EventHandler<ActionEvent> returnButtonHandler = actionEvent -> this.returnButtonPressed();

    // Methods
}
