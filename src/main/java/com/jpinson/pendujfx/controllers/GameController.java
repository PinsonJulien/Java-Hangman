package com.jpinson.pendujfx.controllers;
import com.jpinson.pendujfx.components.keyboard.KeyboardKeyListener;
import com.jpinson.pendujfx.components.word.Word;
import com.jpinson.pendujfx.views.GameView;

public class GameController extends Controller implements KeyboardKeyListener {
    private final GameView view;

    public GameController() {
        this.view = new GameView(this);
    }

    public GameView getView() {
        return this.view;
    }

    @Override
    public void KeyboardPressedKey(char c) {
        System.out.println(c);
        Word word = this.view.getWord();
        String wordBeforeSwap = word.getLettersToString();
        word.swapLetters(c);
        String wordAfterSwap = word.getLettersToString();

        // Letters have been uncovered
        if (!(wordBeforeSwap.equals(wordAfterSwap))) {

            // All the letters are uncovered, end the game
            if (word.getWord().equals(wordAfterSwap)) {
                System.out.println("You won !");
            }
        }

        // No letter was unconvered, lose life
        else {
            System.out.println("-1 vie !");
        }

    }
}
