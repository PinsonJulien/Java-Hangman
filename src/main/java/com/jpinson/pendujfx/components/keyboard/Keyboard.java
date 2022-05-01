package com.jpinson.pendujfx.components.keyboard;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

// Keyboard able to have any set of keys
public class Keyboard extends GridPane implements InitResetInterface {
    private final KeyboardKeyListener listener;
    private final char[] characters;
    private final Map<Character, Key> keys = new HashMap<>();
    private final int columns;

    public Keyboard(KeyboardKeyListener listener, char[] characters, int columns) {
        this.listener = listener;
        this.characters = characters;
        this.columns = columns;
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        int x = 0;
        int y = 0;

        for (char c : this.characters) {
            Key key = new Key(c);
            key.setOnAction(buttonHandler);
            keys.put(c, key);

            this.add(key, x, y);

            if (x == columns-1) {
                x = 0;
                ++y;
            } else {
                ++x;
            }
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

    public boolean isKeyDisabled (char c) {
        return this.keys.get(c).isDisabled();
    }

    // Events
    private final EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Key key = (Key) actionEvent.getSource();
            listener.keyboardPressedKey(key.getValue());
        }
    };

}
