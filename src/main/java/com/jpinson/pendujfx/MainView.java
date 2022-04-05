package com.jpinson.pendujfx;

import com.jpinson.pendujfx.views.View;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class MainView extends View {
    public MainView() {
        this.getChildren().add(new Button("bonjour"));
        //this.getChildren().clear();
    }
}
