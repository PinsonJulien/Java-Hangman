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
    public void init() {
    }

    @Override
    public void reset() {
        GameOverView view = this.getView();
        int score = this.gameModel.getScore();

        if (score >= 0) view.setWin(score);
        else view.setLoose(-score);
    }

    // Listeners
    @Override
    public void ReplayButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.GAME);
    }

    @Override
    public void MenuButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.MENU);
    }

    @Override
    public void ScoreButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.SCORES);
    }

    // Methods
}
