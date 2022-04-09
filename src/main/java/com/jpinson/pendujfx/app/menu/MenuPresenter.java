package com.jpinson.pendujfx.app.menu;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;

public class MenuPresenter
    extends ChildPresenter<AppPresenterListener, MenuView>
    implements MenuViewListener
{

    public MenuPresenter(AppPresenterListener listener) {
        super(listener, new MenuView());
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.getView().addListener(this);
    }

    @Override
    public void reset() {}

    // Listeners

    @Override
    public void playButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.GAME);
    }

    @Override
    public void optionButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.OPTIONS);
    }

    // Methods
}
