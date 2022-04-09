package com.jpinson.pendujfx.app;

import com.jpinson.pendujfx.app.gameOver.GameOverPresenter;
import com.jpinson.pendujfx.app.menu.MenuPresenter;
import com.jpinson.pendujfx.app.options.OptionsPresenter;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.app.game.GamePresenter;
import com.jpinson.pendujfx.framework.presenter.ParentPresenter;

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

        // Add all views
        this.addChildPresenter(PresenterEnum.MENU, new MenuPresenter(this));
        this.addChildPresenter(PresenterEnum.OPTIONS, new OptionsPresenter(this));
        this.addChildPresenter(PresenterEnum.GAME, new GamePresenter(this, gameModel));
        this.addChildPresenter(PresenterEnum.GAMEOVER, new GameOverPresenter(this));

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
