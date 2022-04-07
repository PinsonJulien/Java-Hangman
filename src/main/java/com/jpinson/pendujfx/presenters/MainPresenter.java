package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.enums.PresenterAlias;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.views.MainView;
import javafx.scene.Node;

public class MainPresenter extends Presenter<MainView> implements MainPresenterListener {
    private final GameModel gameModel;
    private final GamePresenter gamePresenter;

    public MainPresenter() {
        super(new MainView());
        this.gameModel = new GameModel();
        this.gamePresenter = new GamePresenter(this);

        //GameView gameView = new GameView(new GamePresenter(this));
        //this.gamePresenter = gameView.getPresenter();
        this.selectPresenter(PresenterAlias.GAME);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    private void insertGamePresenter() {
        this.getView().insertNode(gamePresenter.getView().getPane());
    }

    private void insertPresenter(Node node) {
        this.getView().insertNode(node);
    }

    private void removeCurrentPresenter() {
        this.getView().removeNodes();
    }

    @Override
    public void selectPresenter(PresenterAlias alias) {
        removeCurrentPresenter();
        Node node = switch (alias) {
            case GAME -> this.gamePresenter.getView().getPane();
        };

        switch (alias) {
            case GAME :
                node = this.gamePresenter.getView().getPane();
                break;
        }

        insertPresenter(node);
    }
}
