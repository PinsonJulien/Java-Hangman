package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.enums.PresenterAlias;
import com.jpinson.pendujfx.views.GameOverView;
import com.jpinson.pendujfx.views.GameOverViewListener;

public class GameOverPresenter extends Presenter<GameOverView> implements GameOverViewListener {
    private final MainPresenter mainPresenter;

    public GameOverPresenter (MainPresenter mainPresenter) {
        super(new GameOverView());
        this.getView().addListener(this);
        this.mainPresenter = mainPresenter;
    }

    // Interfaces
    @Override
    public void init() {

    }

    @Override
    public void reset() {

    }


    // Listeners
    @Override
    public void NewGameButtonPressed() {
        this.mainPresenter.selectPresenter(PresenterAlias.GAME);
    }

    @Override
    public void MenuButtonPressed() {

    }
}
