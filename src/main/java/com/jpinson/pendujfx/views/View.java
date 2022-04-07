package com.jpinson.pendujfx.views;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public abstract class View<P extends Pane, L> {
    private final P pane;
    private final ArrayList<L> listeners = new ArrayList<>();

    public View (P pane) {
        this.pane = pane;
    }

    public P getPane() {
        return pane;
    }

    public void insertNode (Node... node) {
        ObservableList<Node> childrenList = this.pane.getChildren();
        childrenList.addAll(node);
    }

    public void removeNode (Node... node) {
        ObservableList<Node> childrenList = this.pane.getChildren();
        childrenList.removeAll(node);
    }

    public void removeNodes () {
        ObservableList<Node> childrenList = this.pane.getChildren();
        childrenList.clear();
    }

    public final ArrayList<L> getListeners() {
        return this.listeners;
    }

    public void addListener(L listener) {
        this.listeners.add(listener);
    }

    public abstract void init();
    public abstract void reset();
}
