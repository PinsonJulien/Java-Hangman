package com.jpinson.pendujfx.app.game;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.OptionsModel;
import com.jpinson.pendujfx.models.UserModel;
import com.jpinson.pendujfx.models.WordModel;
import com.jpinson.pendujfx.services.ScoreService;
import com.jpinson.pendujfx.services.WordService;
import com.jpinson.pendujfx.utils.EncryptedWord;

import java.sql.SQLException;

public class GamePresenter
    extends ChildPresenter<AppPresenterListener, GameView>
    implements GameViewListener
{
    private final GameModel gameModel;
    private final UserModel userModel;
    private final OptionsModel optionsModel;

    private final WordService wordService = new WordService();
    private final ScoreService scoreService = new ScoreService();

    private static final char encryptingCharacter = '?';
    private static final int maxHealth = 5;

    public GamePresenter(
        GameView gameView,
        AppPresenterListener listener,
        GameModel gameModel,
        OptionsModel optionsModel,
        UserModel userModel
    ) {
        super(gameView, listener);
        this.gameModel = gameModel;
        this.optionsModel = optionsModel;
        this.userModel = userModel;

        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
    }

    @Override
    public void reset() {
        this.newGame();
        this.getView().reset();
    }

    // Listeners
    @Override
    public void KeyboardPressedKey(char c) {
        EncryptedWord encryptedWord = this.gameModel.getEncryptedWord();
        GameView view = this.getView();

        // Incorrect letter
        if (!encryptedWord.contains(c)) {
            // remove health
            int health = this.gameModel.getHealth() - 1;

            if (health <= 0) {
                this.gameLost();
                return;
            }

            this.gameModel.setHealth(health);
            // Set health in percentage.
            double percentage = ((double) health/maxHealth)*100;
            view.getHealthBar().setHealth(percentage);
            return;
        }

        // Reveal letter in view
        encryptedWord.decrypt(c);
        view.getWord().set(encryptedWord.get());

        // All letters are revealed : game over, win
        if (encryptedWord.isDecrypted()) {
            this.gameWon();
        }
    }

    @Override
    public void forfeitButtonPressed() {
        this.gameModel.setHealth(0);
        this.gameLost();
    }

    // Methods
    private void newGame() {
        // Get a random word, difficulty increases length and complexity
        String word = "";

        try {
            // Get a random word and set the score of the game.
            WordModel wordModel = this.wordService.getRandomWord(5, 6);

            this.gameModel.setScore(wordModel.getScore());
            word = wordModel.getWord();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        word = word.toUpperCase();

        // Encrypt the word
        this.gameModel.setEncryptedWord(new EncryptedWord(word, encryptingCharacter));

        // Set health to max
        this.gameModel.setHealth(maxHealth);

        // Setup view
        GameView view = this.getView();
        view.getWord().set(this.gameModel.getEncryptedWord().get());
        view.getHealthBar().setFullHealth();
    }

    private void gameWon() {
        this.insertScore(this.gameModel.getScore());
        this.gameOver();
    }

    private void gameLost() {
        int score = this.gameModel.getScore();
        this.gameModel.setScore(-score);
        this.insertScore(-score);
        this.gameOver();
    }

    private void gameOver() {
        // Reset the view
        this.getView().reset();

        // Switch to game-over view.
        this.getParentListener().selectPresenter(PresenterEnum.GAMEOVER);
    }

    private void insertScore(int score) {
        try {
            this.scoreService.addScore(
                score,
                this.optionsModel.getDifficulty(),
                this.userModel
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
