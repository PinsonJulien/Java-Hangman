package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.views.GameView;
import com.jpinson.pendujfx.views.GameViewListener;

public class GamePresenter extends Presenter<GameView> implements GameViewListener {
    private final MainPresenter mainPresenter;
    private final GameModel gameModel;

    private static final char encryptingCharacter = '?';

    public GamePresenter(MainPresenter mainPresenter) {
        super(new GameView());
        this.getView().addListener(this);

        this.mainPresenter = mainPresenter;
        this.gameModel = this.mainPresenter.getGameModel();

        // Setup new game
        this.newGame();
    }

    public void newGame() {
        // Get a random word depending from difficulty
        String word = "Patate";

        // Register and crypt
        word = word.toUpperCase();
        this.gameModel.setWord(word);
        String encryptedWord = this.encryptWord(word);
        this.gameModel.setEncryptedWord(encryptedWord);

        // Setup view
        GameView view = this.getView();
        view.getWord().set(encryptedWord);
    }

    public void resetView() {
        GameView view = this.getView();

        // Reset keyboard
        view.getKeyboard().reset();
    }

    public String encryptWord(String word) {
        return word.replaceAll("[a-zA-Z]", String.valueOf(encryptingCharacter));
    }

    public void decryptWord(char c) {
        String word = this.gameModel.getWord();
        char[] encryptedWord = this.gameModel.getEncryptedWord().toCharArray();

        int len = word.length();
        for (int i = 0 ; i < len; ++i) {
            if (word.charAt(i) == c) encryptedWord[i] = c;
        }

        this.gameModel.setEncryptedWord(String.valueOf(encryptedWord));
    }

    // ----------------------------------------------
    // Listeners
    // ----------------------------------------------
    @Override
    public void KeyboardPressedKey(char c) {
        String word = this.gameModel.getWord();

        // Incorrect letter
        if (word.indexOf(c) < 0 ) {
            // remove health
            System.out.println("Outch -1 vie !");
            return;
        }

        // Reveal letter in view
        decryptWord(c);
        String encryptedWord = this.gameModel.getEncryptedWord();
        this.getView().getWord().set(encryptedWord);

        // All letters are revealed : game over, win
        if ( word.equals(encryptedWord) ) {
            this.resetView();
            System.out.println("You won");
        }


        //Word word = this.view.getWord();
        // word.contains();

        // Comparer avec string.contains();
        /*String wordBeforeSwap = word.getLettersToString();
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
        }*/

    }
}
