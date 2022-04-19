package com.jpinson.pendujfx.app.menu;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.UserModel;
import javafx.application.Platform;

public class MenuPresenter
    extends ChildPresenter<AppPresenterListener, MenuView>
    implements MenuViewListener
{
    private final UserModel userModel;

    public MenuPresenter(
        MenuView menuView,
        AppPresenterListener listener,
        UserModel userModel
    ) {
        super(menuView, listener);
        this.userModel = userModel;
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {}

    @Override
    public void reset() {
        // When a game has been played, show the quickplay button.
        if (!this.userModel.getName().equals("")) {
            this.view.revealQuickPlayButton();
        }
    }

    // Listeners

    @Override
    public void quickPlayButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.GAME);
    }

    @Override
    public void startButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.OPTIONS);
    }

    @Override
    public void scoresButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.SCORES);
    }

    @Override
    public void quitButtonPressed() {
        // Quit the app.
        Platform.exit();
    }

    // Methods
}
