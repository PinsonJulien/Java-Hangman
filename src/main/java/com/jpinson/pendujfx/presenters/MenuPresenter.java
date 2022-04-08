package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.enums.PresenterAlias;
import com.jpinson.pendujfx.views.MenuView;
import com.jpinson.pendujfx.views.MenuViewListener;

public class MenuPresenter extends Presenter<MenuView> implements MenuViewListener {
    private final MainPresenter mainPresenter;

    public MenuPresenter(MainPresenter mainPresenter) {
        super(new MenuView());
        this.mainPresenter = mainPresenter;
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
        this.mainPresenter.selectPresenter(PresenterAlias.GAME);
    }

    @Override
    public void optionButtonPressed() {
        // this.mainPresenter.selectPresenter(PresenterAlias.OPTION);
    }

    // Methods
}
