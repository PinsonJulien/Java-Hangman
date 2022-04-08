package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.enums.PresenterAlias;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.views.MainView;

public class MainPresenter extends Presenter<MainView> implements MainPresenterListener {
    private final GameModel gameModel;
    private final GamePresenter gamePresenter;
    private final GameOverPresenter gameOverPresenter;

    public MainPresenter() {
        super(new MainView());
        this.gameModel = new GameModel();
        this.gamePresenter = new GamePresenter(this);
        this.gameOverPresenter = new GameOverPresenter(this);

        this.selectPresenter(PresenterAlias.GAME);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    private void removeCurrentPresenter() {
        this.getView().removeNodes();
    }

    // Interfaces
    @Override
    public void init() {}

    @Override
    public void reset() {}

    // Listeners
    @Override
    public void selectPresenter(PresenterAlias alias) {
        removeCurrentPresenter();
        Presenter<?> presenter = switch (alias) {
            case GAME -> this.gamePresenter;
            case GAMEOVER -> this.gameOverPresenter;
        };

        presenter.reset();
        this.getView().insertNode(presenter.getView().getPane());
    }
}
