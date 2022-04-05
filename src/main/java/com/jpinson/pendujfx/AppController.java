package com.jpinson.pendujfx;

import com.jpinson.pendujfx.components.keyboard.Key;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AppController {
    @FXML
    private Label welcomeText;

    @FXML
    private VBox box;

    @FXML
    protected void initialize() {
        Key key = new Key('s');
        //key.setOnAction("#onHelloBu");
        box.getChildren().add(key);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
