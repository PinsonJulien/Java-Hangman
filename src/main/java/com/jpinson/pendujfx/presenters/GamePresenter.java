package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.views.GameView;
import com.jpinson.pendujfx.views.GameViewListener;

public class GamePresenter extends Presenter<GameView> implements GameViewListener {
    private final MainPresenter mainPresenter;
    private final GameModel gameModel;

    private static final char encryptingCharacter = '?';
    private static final int maxHealth = 5;

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

        // Set health to max
        this.gameModel.setHealth(maxHealth);

        // Setup view
        GameView view = this.getView();
        view.getWord().set(encryptedWord);
        view.getHealthBar().setFullHealth();
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
        GameView view = this.getView();

        // Incorrect letter
        if (word.indexOf(c) < 0 ) {
            // remove health
            int health = this.gameModel.getHealth() - 1;

            if (health <= 0) {
                System.out.printf("GAME OVER !");
                view.gameOver();
                return;
            }

            this.gameModel.setHealth(health);
            // Set health in percentage.
            double percentage = ((double) health/maxHealth)*100;
            view.getHealthBar().setHealth(percentage);
            return;
        }

        // Reveal letter in view
        decryptWord(c);
        String encryptedWord = this.gameModel.getEncryptedWord();
        view.getWord().set(encryptedWord);

        // All letters are revealed : game over, win
        if ( word.equals(encryptedWord) ) {
            view.reset();
            System.out.println("You won");
        }
    }
}
