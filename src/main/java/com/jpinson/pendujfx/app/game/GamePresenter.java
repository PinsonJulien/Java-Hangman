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

import java.io.IOException;
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
        this.view.reset();
    }

    // Listeners
    @Override
    public void keyboardPressedKey(char c) {
        EncryptedWord encryptedWord = this.gameModel.getEncryptedWord();

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
            double percentage = ((double) health/this.gameModel.getMaxHealth())*100;
            this.view.setHealth(percentage);
            return;
        }

        // Reveal letter in view
        encryptedWord.decrypt(c);
        this.view.setWord(encryptedWord.get());

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
        // Set variables by difficulty
        final int minWordLength;
        final int maxWordLength;
        switch (this.optionsModel.getDifficulty()) {
            default:
            case EASY:
                minWordLength = 4;
                maxWordLength = 6;
                break;

            case NORMAL:
                minWordLength = 8;
                maxWordLength = 10;
                break;

            case HARD:
                minWordLength = 12;
                maxWordLength = 14;
                break;

            case INSANE:
                minWordLength = 16;
                maxWordLength = 20;
                break;
        }


        // Get a random word, difficulty increases length.
        WordModel wordModel = (this.optionsModel.isNetworkEnabled())
            ? this.fetchRandomWordOnline(minWordLength, maxWordLength)
            : this.fetchRandomWord(minWordLength, maxWordLength);

        this.gameModel.setScore(wordModel.getScore());
        String word = wordModel.getWord().toUpperCase();

        // Encrypt the word
        this.gameModel.setEncryptedWord(new EncryptedWord(word, optionsModel.getEncryptingCharacter()));

        // Set health to max
        this.gameModel.setHealth(this.gameModel.getMaxHealth());

        // Setup view
        this.view.setScoreValue(String.valueOf(this.gameModel.getScore()));
        this.view.setWord(this.gameModel.getEncryptedWord().get());
        this.view.setFullHealth();
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
        // Switch to game-over view.
        this.parentListener.selectPresenter(PresenterEnum.GAMEOVER);
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

    private WordModel fetchRandomWord(int min, int max) {
        try {
            // Get a random word and set the score of the game.
            return this.wordService.getRandomWord(min, max);
        } catch (SQLException ignored) {
            // Database fetch had an issue, we put a placeholder.
            return new WordModel(
                0,
                "I AM ERROR.",
                0
            );
        }
    }

    private WordModel fetchRandomWordOnline(int min, int max) {
        try {
            return this.wordService.getRandomWordOnline(min, max);
        } catch (IOException | InterruptedException e) {
            // Failed -> Remove network and fetch from database.
            this.optionsModel.setNetwork(false);

            return this.fetchRandomWord(min, max);
        }
    }
}
