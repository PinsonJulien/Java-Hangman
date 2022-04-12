package com.jpinson.pendujfx.app.gameOver;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;

public class GameOverPresenter
    extends ChildPresenter<AppPresenterListener, GameOverView>
    implements GameOverViewListener
{
    public GameOverPresenter (
        GameOverView gameOverView,
        AppPresenterListener listener
    ) {
        super(gameOverView, listener);
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
    }

    @Override
    public void reset() {}

    // Listeners
    @Override
    public void NewGameButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.GAME);
    }

    @Override
    public void MenuButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.MENU);
    }

    // Methods
}
