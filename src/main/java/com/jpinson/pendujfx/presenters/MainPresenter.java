package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.enums.PresenterAlias;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.views.MainView;

public class MainPresenter extends Presenter<MainView> implements MainPresenterListener {
    // Models
    private final GameModel gameModel = new GameModel();

    // Presenters
    private final MenuPresenter menuPresenter = new MenuPresenter(this);
    private final GamePresenter gamePresenter =  new GamePresenter(this);
    private final GameOverPresenter gameOverPresenter = new GameOverPresenter(this);

    public MainPresenter() {
        super(new MainView());
        this.init();
    }

    // Getters / Setters
    public GameModel getGameModel() {
        return gameModel;
    }

    // Interfaces
    @Override
    public void init() {
        this.selectPresenter(PresenterAlias.MENU);
    }

    @Override
    public void reset() {}

    // Listeners
    @Override
    public void selectPresenter(PresenterAlias alias) {
        removeCurrentPresenter();
        Presenter<?> presenter = switch (alias) {
            case MENU -> this.menuPresenter;
            case GAME -> this.gamePresenter;
            case GAMEOVER -> this.gameOverPresenter;
        };

        presenter.reset();
        this.getView().insertNode(presenter.getView().getPane());
    }

    // Methods
    private void removeCurrentPresenter() {
        this.getView().removeNodes();
    }

}
