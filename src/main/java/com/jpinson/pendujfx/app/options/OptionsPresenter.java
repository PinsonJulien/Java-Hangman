package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.components.difficultyComboBox.DifficultyComboBox;
import com.jpinson.pendujfx.components.formFields.ComboBoxFormField;
import com.jpinson.pendujfx.components.formFields.TextFormField;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.OptionsModel;
import com.jpinson.pendujfx.models.UserModel;
import com.jpinson.pendujfx.services.UserService;
import com.jpinson.pendujfx.utils.Alphanumeric;
import java.sql.SQLException;

public class OptionsPresenter
    extends ChildPresenter<AppPresenterListener, OptionsView>
    implements OptionsViewListener
{
    private final OptionsModel optionsModel;
    private final UserModel userModel;

    private final UserService userService = new UserService();

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
        this.reset();
    }

    @Override
    public void reset() {
        this.view.getUsernameField().setText(this.userModel.getName());
        this.view.getDifficultyField().getField().setValue(this.optionsModel.getDifficulty());
        this.view.reset();
    }

    // Listeners

    @Override
    public void returnButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.MENU);
    }

    @Override
    public void playButtonPressed() {
        boolean formValidity = true;
        final TextFormField usernameField = this.view.getUsernameField();
        final String username = usernameField.getText();

        final ComboBoxFormField<DifficultyComboBox> difficultyField = this.view.getDifficultyField();
        final DifficultyEnum difficulty = difficultyField.getField().getValue();

        String usernameMessage = this.validateUsernameField(username);
        String difficultyMessage = this.validateDifficultyField(difficulty);

        if (usernameMessage != null) {
            usernameField.setInvalid(usernameMessage);
            formValidity = false;
        } else {
            usernameField.setValid();
        }

        if (difficultyMessage != null) {
            difficultyField.setInvalid(difficultyMessage);
            formValidity = false;
        } else {
            difficultyField.setValid();
        }

        // If all fields are validated, set the data and show the game.
        if (formValidity) {
            this.optionsModel.setDifficulty(difficulty);

            // Get the user model, if the user doesn't exist yet, create it.
            UserModel userModel = this.getOrAddUser(username);
            this.userModel.setName(userModel.getName());
            this.userModel.setId(userModel.getId());

            this.parentListener.selectPresenter(PresenterEnum.GAME);
        }
    }

    // Methods
    private String validateUsernameField(String username) {
        final int len = username.length();

        // min 1, max 20
        if (len < 1 || len > 20) {
            return "The username length should be between 1 and 20.";
        }

        // only allow alphanumeric
        if (!Alphanumeric.validate(username)) {
            return "The username cannot contains special characters.";
        }

        return null;
    }

    private String validateDifficultyField(DifficultyEnum difficulty) {
        return null;
    }

    private UserModel getOrAddUser(String username) {
        try {
            this.userService.addUser(username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            return this.userService.getUser(username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new UserModel();
        }
    }
}
