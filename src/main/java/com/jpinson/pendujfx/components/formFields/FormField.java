package com.jpinson.pendujfx.components.formFields;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// Allow to make common looking form fields
// Have a label holding the name of the field,
// Have provided field element
// Have a label dealing with error messages
// Controls color of elements by CSS when set as successful or error.

public abstract class FormField <F extends Control> extends VBox {
    private final Label fieldLabel = new Label();
    private final Label errorLabel = new Label();
    protected final F field;

    private static final String validColorClass = "valid";
    private static final String invalidColorClass = "invalid";

    public FormField (F field, String label) {
        this.field = field;
        this.fieldLabel.setText(label);

        this.getChildren().addAll(
                this.fieldLabel,
                this.field,
                this.errorLabel
        );

        this.errorLabel.getStyleClass().add(invalidColorClass);
        this.errorLabel.setVisible(false);
    }

    public void setNeutral() {
        this.errorLabel.setVisible(false);
        this.removeStyleClasses(invalidColorClass);
        this.removeStyleClasses(validColorClass);
    }

    public void setValid() {
        this.swapStyleClasses(invalidColorClass, validColorClass);
        this.field.getStyleClass().add(validColorClass);
        this.fieldLabel.getStyleClass().add(validColorClass);
        this.errorLabel.setVisible(false);
    }

    public void setInvalid(String errorMessage) {
        this.swapStyleClasses(validColorClass, invalidColorClass);
        this.errorLabel.setText(errorMessage);
        this.errorLabel.setVisible(true);
    }

    private void swapStyleClasses(String oldClass, String newClass) {
        this.removeStyleClasses(oldClass);
        this.addStyleClasses(newClass);
    }

    private void addStyleClasses(String cssClass) {
        this.field.getStyleClass().add(cssClass);
        this.fieldLabel.getStyleClass().add(cssClass);
    }

    private void removeStyleClasses(String cssClass) {
        this.field.getStyleClass().remove(cssClass);
        this.fieldLabel.getStyleClass().remove(cssClass);
    }
}
