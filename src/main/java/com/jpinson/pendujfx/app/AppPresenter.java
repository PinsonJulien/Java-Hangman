package com.jpinson.pendujfx.app;

import com.jpinson.pendujfx.app.gameOver.GameOverPresenter;
import com.jpinson.pendujfx.app.menu.MenuPresenter;
import com.jpinson.pendujfx.app.options.OptionsPresenter;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.app.game.GamePresenter;
import com.jpinson.pendujfx.framework.presenter.ParentPresenter;
import com.jpinson.pendujfx.models.OptionsModel;
import com.jpinson.pendujfx.models.PlayerModel;

import java.util.EnumMap;

public class AppPresenter
    extends ParentPresenter
    <
        AppView,
        PresenterEnum,
        ChildPresenter<?, ?>
    >
    implements AppPresenterListener
{
    public AppPresenter() {
        super(new AppView(), new EnumMap<>(PresenterEnum.class));

        // Generate all models :
        GameModel gameModel = new GameModel();
        OptionsModel optionsModel = new OptionsModel();
        PlayerModel playerModel = new PlayerModel();

        MenuPresenter menuPresenter = new MenuPresenter(this);
        OptionsPresenter optionsPresenter = new OptionsPresenter(this, optionsModel, playerModel);
        GamePresenter gamePresenter = new GamePresenter(this, gameModel, optionsModel, playerModel);
        GameOverPresenter gameOverPresenter = new GameOverPresenter(this);

        // Add all views
        this.addChildPresenter(PresenterEnum.MENU, menuPresenter);
        this.addChildPresenter(PresenterEnum.OPTIONS, optionsPresenter);
        this.addChildPresenter(PresenterEnum.GAME, gamePresenter);
        this.addChildPresenter(PresenterEnum.GAMEOVER, gameOverPresenter);

        // Set first view
        this.changeView(PresenterEnum.MENU);
        this.init();
    }

    // Interfaces
    @Override
    public void init() {

    }

    @Override
    public void reset() {

    }
}
