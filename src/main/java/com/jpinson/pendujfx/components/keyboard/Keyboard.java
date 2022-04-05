package com.jpinson.pendujfx.components.keyboard;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Keyboard extends FlowPane {
    private final KeyboardKeyListener listener;
    private final List<Character> charactersList = new ArrayList<>();
    private final Map<Character, Key> keys = new HashMap<>();

    public Keyboard(List<Character> charactersList, KeyboardKeyListener listener) {
        this.listener = listener;
        this.charactersList.addAll(charactersList);
        this.initComponents();
    }

    public Map<Character, Key> getKeys() {
        return keys;
    }

    private void initComponents() {
        ObservableList<Node> childrenList = this.getChildren();

        for (Character c : charactersList) {
            Key key = new Key(c);
            key.setOnAction(buttonHandler);

            childrenList.add(key);
            keys.put(c, key);
        }
    }

    private final EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Key key = (Key) actionEvent.getSource();
            listener.KeyboardPressedKey(key.getValue());
            key.setDisable(true);
        }
    };

}
