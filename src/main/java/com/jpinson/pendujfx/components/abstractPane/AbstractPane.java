package com.jpinson.pendujfx.components.abstractPane;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class AbstractPane<P extends Pane> extends Pane {
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
