package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.OptionsModel;
import com.jpinson.pendujfx.models.PlayerModel;
import com.jpinson.pendujfx.utils.Alphanumeric;

public class OptionsPresenter
    extends ChildPresenter<AppPresenterListener, OptionsView>
    implements OptionsViewListener
{
    private final OptionsModel optionsModel;
    private final PlayerModel playerModel;

    public OptionsPresenter(AppPresenterListener listener, OptionsModel optionsModel, PlayerModel playerModel) {
        super(listener, new OptionsView());
        this.optionsModel = optionsModel;
        this.playerModel = playerModel;

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
    public void menuButtonPressed() {
        this.getParentListener().selectPresenter(PresenterEnum.MENU);
    }

    @Override
    public void validateButtonPressed(DifficultyEnum difficulty, String username) {
        this.optionsModel.setDifficulty(difficulty);

        // Username validation, only allow alphanumeric
        if (!Alphanumeric.validate(username)) {
            this.getView().setError("The username cannot contains special characters.");
            return;
        }

        this.playerModel.setName(username);
    }

    // Methods
}
