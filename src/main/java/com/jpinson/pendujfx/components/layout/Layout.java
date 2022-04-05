package com.jpinson.pendujfx.components.layout;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

// Holds the "shell" of the application
public class Layout extends VBox {
    private final MenuBar menuBar;
    private final VBox body;

    public Layout() {
        this.menuBar = new MenuBar(new Menu("Patates"));
        this.body = new VBox();
        ObservableList<Node> childrenList = this.getChildren();
        childrenList.addAll(this.menuBar, this.body);
    }

    public VBox getBody () {
        return this.body;
    }
}
