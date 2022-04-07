package com.jpinson.pendujfx.views;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MenuView extends View<Pane, Void> {
    // Sert de hub aux options etc.

    // sélection de difficulté
    // internet (afficher si oui ou non connecté), possibilité de désactivé

    // tableau des scores (autre vue)

    private final Button button;

    public MenuView() {
        super(new Pane());
        this.button = new Button("bonjour");
        Button button2 = new Button("Pinçon");
        this.insertNode(this.button, button2);
    }

    @Override
    public void init() {

    }

    @Override
    public void reset() {

    }
}
