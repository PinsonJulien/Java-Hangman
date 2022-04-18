package com.jpinson.pendujfx.components.formFields;

import com.jpinson.pendujfx.utils.CssClass;
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
        CssClass.remove(this.field, validColorClass, invalidColorClass);
        CssClass.remove(this.fieldLabel, validColorClass, invalidColorClass);

        this.errorLabel.setVisible(false);
    }

    public void setValid() {
        CssClass.swap(this.field, invalidColorClass, validColorClass);
        CssClass.swap(this.fieldLabel, invalidColorClass, validColorClass);

        this.errorLabel.setVisible(false);
    }

    public void setInvalid(String errorMessage) {
        CssClass.swap(this.field, validColorClass, invalidColorClass);
        CssClass.swap(this.fieldLabel, validColorClass, invalidColorClass);

        this.errorLabel.setText(errorMessage);
        this.errorLabel.setVisible(true);
    }
}
