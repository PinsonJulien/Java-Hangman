package com.jpinson.pendujfx.app.game;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.MusicEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.GameModel;
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
    private final WordService wordService;
    private final ScoreService scoreService;

    public GamePresenter(
        GameView gameView,
        AppPresenterListener listener,
        GameModel gameModel,
        WordService wordService,
        ScoreService scoreService
    ) {
        super(gameView, listener);
        this.gameModel = gameModel;
        this.wordService = wordService;
        this.scoreService = scoreService;
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
        this.parentListener.selectMusic(MusicEnum.GAME);
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
                this.gameOver(false);
                return;
            }

            this.gameModel.setHealth(health);
            // Set health in percentage.
            this.view.setHealth(health, this.gameModel.getMaxHealth());
            return;
        }

        // Reveal letter in view
        encryptedWord.decrypt(c);
        this.view.updateWord(this.gameModel.getEncryptedWord().getLetters());

        // All letters are revealed : game over, win
        if (encryptedWord.isDecrypted()) {
            this.gameOver(true);
        }
    }

    @Override
    public void forfeitButtonPressed() {
        this.gameOver(false);
    }

    // Methods
    private void newGame() {
        // Set variables by difficulty
        final int minWordLength;
        final int maxWordLength;
        switch (this.gameModel.getOptions().getDifficulty()) {
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

        // Get a random word, from either API or database.
        WordModel wordModel = (this.gameModel.getOptions().isNetworkEnabled())
            ? this.fetchRandomWordOnline(minWordLength, maxWordLength)
            : this.fetchRandomWord(minWordLength, maxWordLength);

        this.gameModel.setWord(wordModel);

        String word = wordModel.get().toUpperCase();

        // Encrypt the word
        this.gameModel.getEncryptedWord().newEncryption(word);

        // Set health to max
        this.gameModel.setHealth(this.gameModel.getMaxHealth());

        // Setup view
        this.view.setScoreValue(String.valueOf(wordModel.getScore()));
        this.view.setWord(this.gameModel.getEncryptedWord().getLetters());
        this.view.setHealthComponent(this.gameModel.getOptions().getHealthComponent());
        this.view.setFullHealth(this.gameModel.getMaxHealth());
    }

    private void gameOver(boolean win) {
        this.gameModel.setStatus(win);

        int score = this.gameModel.getWord().getScore();
        if (!win) score *= -1;
        this.insertScore(score);

        // Switch to game-over view.
        this.parentListener.selectPresenter(PresenterEnum.GAMEOVER);
    }

    private void insertScore(int score) {
        try {
            this.scoreService.addScore(
                score,
                this.gameModel.getOptions().getDifficulty(),
                this.gameModel.getUser()
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
            this.gameModel.getOptions().setNetwork(false);

            return this.fetchRandomWord(min, max);
        }
    }
}
