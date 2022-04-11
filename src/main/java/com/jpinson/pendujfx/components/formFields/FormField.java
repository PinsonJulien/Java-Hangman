package com.jpinson.pendujfx.components.formFields;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class FormField <F extends Control> extends VBox {
    private final Label fieldLabel = new Label();
    private final Label errorLabel = new Label();
    protected final F field;

    private static final String validColor = "green";
    private static final String invalidColor = "red";

    public FormField (F field, String label) {
        this.field = field;
        this.fieldLabel.setText(label);

        this.getChildren().addAll(
                this.fieldLabel,
                this.field,
                this.errorLabel
        );

        this.errorLabel.setStyle("-fx-text-fill: " + invalidColor);
        this.errorLabel.setVisible(false);
    }

    public void setNeutral() {
        this.field.setStyle("");
    }

    public void setValid() {
        colorComponents(validColor);
        this.errorLabel.setVisible(false);
    }

    public void setInvalid(String errorMessage) {
        colorComponents(invalidColor);
        this.errorLabel.setText(errorMessage);
        this.errorLabel.setVisible(true);
    }

    private void colorComponents(String color) {
        this.field.setStyle("-fx-border-color: " + color);
        this.fieldLabel.setStyle("-fx-text-fill: " + color);
    }

}
