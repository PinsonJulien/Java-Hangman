package com.jpinson.pendujfx.components.keyboard;
import com.jpinson.pendujfx.interfaces.InitResetInterface;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.util.HashMap;
import java.util.Map;

// Keyboard able to have any set of keys
public class Keyboard extends FlowPane implements InitResetInterface {
    private final KeyboardKeyListener listener;
    private final char[] characters;
    private final Map<Character, Key> keys = new HashMap<>();
    private final boolean disableKeyOnUse;

    public Keyboard(KeyboardKeyListener listener, char[] characters) {
        this.listener = listener;
        this.disableKeyOnUse = false;
        this.characters = characters;
        this.init();
    }

    public Keyboard(KeyboardKeyListener listener, char[] characters, boolean disableKeyOnUse) {
        this.listener = listener;
        this.disableKeyOnUse = disableKeyOnUse;
        this.characters = characters;
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        ObservableList<Node> childrenList = this.getChildren();

        for (char c : this.characters) {
            Key key = new Key(c);
            key.setOnAction(buttonHandler);

            childrenList.add(key);
            keys.put(c, key);
        }
    }

    @Override
    public void reset() {
        toggleAllKeys(true);
    }

    // Methods
    public void toggleAllKeys(boolean active) {
        this.keys.forEach((c, key) -> key.setDisable(!active));
    }

    public void toggleKey(char c, boolean active) {
        this.keys.get(c).setDisable(!active);
    }

    // Events
    private final EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Key key = (Key) actionEvent.getSource();
            if (disableKeyOnUse) key.setDisable(true);

            listener.KeyboardPressedKey(key.getValue());
        }
    };

}
