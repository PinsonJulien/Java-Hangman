package com.jpinson.pendujfx.views;
import com.jpinson.pendujfx.components.keyboard.AlphabeticKeyboard;
import com.jpinson.pendujfx.components.word.Word;
import com.jpinson.pendujfx.controllers.GameController;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class GameView extends View {
    private final AlphabeticKeyboard keyboard;
    private final Word word;

    public GameView(GameController controller) {
        this.keyboard = new AlphabeticKeyboard(controller);
        this.word = new Word("Banane");
        this.initComponents();
    }

    public AlphabeticKeyboard getKeyboard() {
        return keyboard;
    }

    public Word getWord() {
        return word;
    }

    public void initComponents() {
        ObservableList<Node> childrenList = this.getChildren();
        childrenList.add(this.keyboard);
        childrenList.add(this.word);
    }
}
