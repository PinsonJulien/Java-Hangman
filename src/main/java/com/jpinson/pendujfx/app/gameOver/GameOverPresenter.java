package com.jpinson.pendujfx.app.gameOver;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.GameModel;

public class GameOverPresenter
    extends ChildPresenter<AppPresenterListener, GameOverView>
    implements GameOverViewListener
{
    private final GameModel gameModel;

    public GameOverPresenter (
        GameOverView gameOverView,
        AppPresenterListener listener,
        GameModel gameModel
    ) {
        super(gameOverView, listener);
        this.gameModel = gameModel;
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {}

    @Override
    public void reset() {
        // Reveals whole encrypted word to show it.
        this.gameModel.getEncryptedWord().decrypt();

        this.view.setResults(
            this.gameModel.getStatus(),
            this.gameModel.getWord().getScore(),
            this.gameModel.getEncryptedWord().getLetters()
        );
    }

    // Listeners
    @Override
    public void ReplayButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.GAME);
    }

    @Override
    public void MenuButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.MENU);
    }

    @Override
    public void ScoreButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.SCORES);
    }

    // Methods
}
