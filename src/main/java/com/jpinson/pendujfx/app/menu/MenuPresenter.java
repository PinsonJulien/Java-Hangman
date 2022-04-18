package com.jpinson.pendujfx.app.menu;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;

public class MenuPresenter
    extends ChildPresenter<AppPresenterListener, MenuView>
    implements MenuViewListener
{

    public MenuPresenter(
        MenuView menuView,
        AppPresenterListener listener
    ) {
        super(menuView, listener);
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
    public void startButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.OPTIONS);
    }

    @Override
    public void scoresButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.SCORES);
    }

    // Methods
}
