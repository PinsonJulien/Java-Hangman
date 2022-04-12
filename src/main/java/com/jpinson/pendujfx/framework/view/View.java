package com.jpinson.pendujfx.framework.view;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

// Views hold the visible part of the application and aspect related methods.
// They have a list of listeners subscribed to it
// The listeners are not obligatory, they can be Void.
public abstract class View
<
    P extends Pane,
    L
>
implements InitResetInterface
{
    private final P pane;
    private final ArrayList<L> listeners = new ArrayList<>();

    public View (P pane) {
        this.pane = pane;
    }

    // Getters / Setters
    public P getPane() {
        return pane;
    }
    public ArrayList<L> getListeners() {
        return this.listeners;
    }

    // Methods
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

    public void addListener(L listener) {
        this.listeners.add(listener);
    }
}
