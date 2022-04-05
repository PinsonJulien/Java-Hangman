package com.jpinson.pendujfx.views;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MenuView extends View {
    // Sert de hub aux options etc.

    // sélection de difficulté
    // internet (afficher si oui ou non connecté), possibilité de désactivé

    // tableau des scores (autre vue)

    private final Button button;

    public MenuView() {
        this.button = new Button("bonjour");
        Button button2 = new Button("Pinçon");
        ObservableList<Node> childrenList = this.getChildren();
        childrenList.addAll(this.button, button2);
    }
}
