package com.jpinson.pendujfx.components.keyboard;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.util.HashMap;
import java.util.Map;

public class Keyboard extends FlowPane {
    private final KeyboardKeyListener listener;
    private final Map<Character, Key> keys = new HashMap<>();
    private final boolean disableKeyOnUse;

    public Keyboard(KeyboardKeyListener listener, char[] characters) {
        this.listener = listener;
        this.disableKeyOnUse = false;
        this.init();
        this.initComponents(characters);
    }

    public Keyboard(KeyboardKeyListener listener, char[] characters, boolean disableKeyOnUse) {
        this.listener = listener;
        this.disableKeyOnUse = disableKeyOnUse;
        this.init();
        this.initComponents(characters);
    }

    private void init() {}

    private void initComponents(char[] characters) {
        ObservableList<Node> childrenList = this.getChildren();

        int len = characters.length;
        for (int i = 0; i < len; ++i) {
            char c = characters[i];
            Key key = new Key(c);
            key.setOnAction(buttonHandler);

            childrenList.add(key);
            keys.put(c, key);
        }
    }

    public void reset() {
        // enable all keys
        this.keys.forEach((c, key) -> {
            key.setDisable(false);
        });
    }

    public void disableKey(char c) {
        this.keys.get(c).setDisable(true);
    }

    public void disableKey(Key key) {
        this.keys.get(key).setDisable(true);
    }

    private final EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Key key = (Key) actionEvent.getSource();
            if (disableKeyOnUse) key.setDisable(true);

            listener.KeyboardPressedKey(key.getValue());
        }
    };

}
