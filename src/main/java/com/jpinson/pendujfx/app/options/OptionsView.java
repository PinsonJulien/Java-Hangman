package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.components.form.field.comboBox.DifficultyComboBox;
import com.jpinson.pendujfx.components.form.field.comboBox.HealthComponentComboBox;
import com.jpinson.pendujfx.components.form.control.ComboBoxFormField;
import com.jpinson.pendujfx.components.form.control.TextFormField;
import com.jpinson.pendujfx.components.pane.ConstrainedGridPane;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.enums.HealthComponentEnum;
import com.jpinson.pendujfx.framework.view.View;
import com.jpinson.pendujfx.utils.CssClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class OptionsView
    extends View<ConstrainedGridPane, OptionsViewListener>
    implements OptionsViewListener
{
    private final Button playButton = new Button("PLAY");
    private final Button returnButton = new Button("RETURN");
    private final Label titleLabel = new Label("OPTIONS");
    private final TextFormField usernameField = new TextFormField("Username");
    private final ComboBoxFormField<DifficultyComboBox> difficultyField
        = new ComboBoxFormField<>(new DifficultyComboBox(DifficultyEnum.EASY), "Difficulty");
    private final ComboBoxFormField<HealthComponentComboBox> healthComponentField
        = new ComboBoxFormField<>(new HealthComponentComboBox(HealthComponentEnum.CLASSIC), "Health style");
    private final CheckBox networkCheckbox = new CheckBox("Network");

    public OptionsView() {
        super(new ConstrainedGridPane());
        this.init();
    }

    // Interfaces
    @Override
    public void init() {
        this.pane.setId("options");

        // set actions
        this.playButton.setOnAction(this.playButtonHandler);
        this.returnButton.setOnAction(this.returnButtonHandler);
        this.networkCheckbox.setOnAction(this.networkToggleHandler);

        // Set placement
        this.pane.setColumns(100);
        this.pane.setRows(20, 60, 20);

        // Title grid
        ConstrainedGridPane titleGrid = new ConstrainedGridPane();
        CssClass.add(titleGrid, "title-grid");
        titleGrid.setColumns(15, 70, 15);
        titleGrid.setRows(100);

        VBox titleButtonPane = new VBox();
        CssClass.add(titleButtonPane, "title-button-pane");
        titleButtonPane.getChildren().add(this.returnButton);
        titleGrid.add(titleButtonPane, 0,0);

        VBox titleLabelPane = new VBox();
        CssClass.add(titleLabelPane, "title-label-pane");
        titleLabelPane.getChildren().add(this.titleLabel);
        titleGrid.add(titleLabelPane, 1, 0);

        VBox titleNetworkPane = new VBox();
        CssClass.add(titleNetworkPane, "title-network-pane");
        titleNetworkPane.getChildren().add(this.networkCheckbox);
        titleGrid.add(titleNetworkPane, 2, 0);

        this.pane.add(titleGrid, 0, 0);

        // Form grid
        ConstrainedGridPane fieldsGrid = new ConstrainedGridPane();
        CssClass.add(fieldsGrid, "field-pane");
        fieldsGrid.setColumns(50, 50);
        fieldsGrid.setRows(100);
        VBox leftFieldsPane = new VBox();
        leftFieldsPane.getChildren().add(this.usernameField);
        CssClass.add(leftFieldsPane, "left-field-pane");
        VBox rightFieldsPane = new VBox();
        rightFieldsPane.getChildren().addAll(
            this.difficultyField,
            this.healthComponentField
        );
        CssClass.add(rightFieldsPane, "right-field-pane");
        fieldsGrid.add(leftFieldsPane, 0, 0);
        fieldsGrid.add(rightFieldsPane, 1, 0);
        this.pane.add(fieldsGrid, 0, 1);

        CssClass.add(this.usernameField, "field");
        CssClass.add(this.difficultyField, "field");
        CssClass.add(this.healthComponentField, "field");

        // Bottom grid
        VBox bottomButtonPane = new VBox();
        CssClass.add(bottomButtonPane, "bottom-button-pane");
        bottomButtonPane.getChildren().add(this.playButton);
        this.pane.add(bottomButtonPane, 0, 2);
        CssClass.add(playButton, "play-button");
    }

    @Override
    public void reset() {
        this.difficultyField.setNeutral();
        this.healthComponentField.setNeutral();
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

    @Override
    public void networkTogglePressed() {
        for (OptionsViewListener listener : getListeners()) {
            listener.networkTogglePressed();
        }
    }

    // Events
    private final EventHandler<ActionEvent> playButtonHandler = actionEvent -> this.playButtonPressed();
    private final EventHandler<ActionEvent> returnButtonHandler = actionEvent -> this.returnButtonPressed();
    private final EventHandler<ActionEvent> networkToggleHandler = actionEvent -> this.networkTogglePressed();

    // Methods
    public void setUsername(String username) {
        this.usernameField.setText(username);
    }

    public String getUsername() {
        return this.usernameField.getText();
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficultyField.getField().setValue(difficulty);
    }

    public DifficultyEnum getDifficulty () {
        return this.difficultyField.getField().getValue();
    }

    public void setDifficultyValid () {
        this.difficultyField.setValid();
    }

    public void setDifficultyInvalid (String message) {
        this.difficultyField.setInvalid(message);
    }

    public void setHealthComponent (HealthComponentEnum healthComponent) {
        this.healthComponentField.getField().setValue(healthComponent);
    }

    public HealthComponentEnum getHealthComponent() {
        return this.healthComponentField.getField().getValue();
    }

    public void setHealthComponentValid () {
        this.healthComponentField.setValid();
    }

    public void setHealthComponentInvalid(String message) {
        this.healthComponentField.setInvalid(message);
    }

    public void setUsernameValid () {
        this.usernameField.setValid();
    }

    public void setUsernameInvalid (String message) {
        this.usernameField.setInvalid(message);
    }

    public void setNetworkVisibility(boolean visibility) {
        this.networkCheckbox.setVisible(visibility);
    }

    public void setNetworkSelected(boolean selected) {
        this.networkCheckbox.setSelected(selected);
    }
}
