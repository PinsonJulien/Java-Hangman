package com.jpinson.pendujfx.views;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class View extends VBox {

    public void addNode(Node node) {
        ObservableList<Node> childrenList = this.getChildren();
        childrenList.add(node);
    }

    public void removeNode(Node node) {
        ObservableList<Node> childrenList = this.getChildren();
        childrenList.remove(node);
    }

    public void removeAllNodes() {
        ObservableList<Node> childrenList = this.getChildren();
        childrenList.clear();
    }
}
