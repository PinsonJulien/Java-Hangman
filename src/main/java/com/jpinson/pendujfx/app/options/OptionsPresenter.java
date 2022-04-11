package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.components.formFields.ComboBoxFormField;
import com.jpinson.pendujfx.components.formFields.TextFormField;
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
    public void validateButtonPressed() {
        this.validateUsernameField();
        this.validateDifficultyField();
    }

    // Methods
    private void validateUsernameField() {
        TextFormField usernameField = this.getView().getUsernameField();
        String username = usernameField.getText();

        // min 1, max 20
        if (username.length() < 1 || username.length() > 20) {
            usernameField.setInvalid("The username length should be between 1 and 20.");
            return;
        }

        // only allow alphanumeric
        if (!Alphanumeric.validate(username)) {
            usernameField.setInvalid("The username cannot contains special characters.");
            return;
        }

        usernameField.setValid();
        this.playerModel.setName(username);
    }

    private void validateDifficultyField() {
        ComboBoxFormField<DifficultyEnum> difficultyField = this.getView().getDifficultyField();
        DifficultyEnum difficulty = difficultyField.getValue();

        difficultyField.setValid();
        this.optionsModel.setDifficulty(difficulty);
    }

}
