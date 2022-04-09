package com.jpinson.pendujfx.app.game;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;

public class GamePresenter
    extends ChildPresenter<AppPresenterListener, GameView>
    implements GameViewListener
{
    private final GameModel gameModel;
    private static final char encryptingCharacter = '?';
    private static final int maxHealth = 5;

    public GamePresenter(AppPresenterListener listener, GameModel gameModel) {
        super(listener, new GameView());
        this.gameModel = gameModel;
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.getView().addListener(this);
    }

    @Override
    public void reset() {
        this.newGame();
        this.getView().reset();
    }

    // Listeners
    @Override
    public void KeyboardPressedKey(char c) {
        String word = this.gameModel.getWord();
        GameView view = this.getView();

        // Incorrect letter
        if (word.indexOf(c) < 0 ) {
            // remove health
            int health = this.gameModel.getHealth() - 1;

            if (health <= 0) {
                this.gameOver();
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

    // Methods
    public void newGame() {
        // Get a random word, difficulty increases length and complexity
        String word = "Potato";

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

    public void gameOver() {
        // Reset the view
        this.getView().reset();

        // Switch to game-over view.
        this.getParentListener().selectPresenter(PresenterEnum.GAMEOVER);
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

}
