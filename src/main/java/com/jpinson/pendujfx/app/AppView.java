package com.jpinson.pendujfx.app;

import com.jpinson.pendujfx.framework.view.View;
import javafx.scene.layout.VBox;

public class AppView extends View<VBox, Void> {
    public AppView() {
        super(new VBox());
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.getPane().setFillWidth(true);
    }

    @Override
    public void reset() {

    }

    // Listeners

    // Methods
}
