package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.components.formFields.ComboBoxFormField;
import com.jpinson.pendujfx.components.formFields.TextFormField;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.OptionsModel;
import com.jpinson.pendujfx.models.UserModel;
import com.jpinson.pendujfx.utils.Alphanumeric;

public class OptionsPresenter
    extends ChildPresenter<AppPresenterListener, OptionsView>
    implements OptionsViewListener
{
    private final OptionsModel optionsModel;
    private final UserModel userModel;

    public OptionsPresenter(
        OptionsView optionsView,
        AppPresenterListener listener,
        OptionsModel optionsModel,
        UserModel userModel
    ) {
        super(optionsView, listener);
        this.optionsModel = optionsModel;
        this.userModel = userModel;

        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.optionsModel.setDifficulty(DifficultyEnum.EASY);
        this.userModel.setName("JeanRandom");
        this.reset();
    }

    @Override
    public void reset() {
        OptionsView view = this.getView();
        view.getUsernameField().setText(this.userModel.getName());
        view.getDifficultyField().setValue(this.optionsModel.getDifficulty());
        view.reset();
    }

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

        int len = username.length();
        // min 1, max 20
        if (len < 1 || len > 20) {
            usernameField.setInvalid("The username length should be between 1 and 20.");
            return;
        }

        // only allow alphanumeric
        if (!Alphanumeric.validate(username)) {
            usernameField.setInvalid("The username cannot contains special characters.");
            return;
        }

        usernameField.setValid();
        this.userModel.setName(username);
    }

    private void validateDifficultyField() {
        ComboBoxFormField<DifficultyEnum> difficultyField = this.getView().getDifficultyField();
        DifficultyEnum difficulty = difficultyField.getValue();

        difficultyField.setValid();
        this.optionsModel.setDifficulty(difficulty);
    }
}
