package com.jpinson.pendujfx.app.options;

import com.jpinson.pendujfx.API.WordnikAPI;
import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.enums.HealthComponentEnum;
import com.jpinson.pendujfx.enums.MusicEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.models.UserModel;
import com.jpinson.pendujfx.services.UserService;
import com.jpinson.pendujfx.utils.Alphanumeric;

import java.sql.SQLException;

public class OptionsPresenter
    extends ChildPresenter<AppPresenterListener, OptionsView>
    implements OptionsViewListener
{
    private final GameModel gameModel;
    private final UserService userService;

    public OptionsPresenter(
        OptionsView optionsView,
        AppPresenterListener listener,
        GameModel gameModel,
        UserService userService
    ) {
        super(optionsView, listener);
        this.gameModel = gameModel;
        this.userService = userService;
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {}

    @Override
    public void reset() {
        // Shows the toggle network button if API is available.
        boolean apiAvailable = new WordnikAPI().isAvailable();
        this.view.setNetworkVisibility(apiAvailable);
        this.setNetwork(apiAvailable);

        UserModel user = this.gameModel.getUser();
        DifficultyEnum difficulty = this.gameModel.getOptions().getDifficulty();
        HealthComponentEnum healthComponent = this.gameModel.getOptions().getHealthComponent();
        boolean network = this.gameModel.getOptions().isNetworkEnabled();

        if (user != null) this.view.setUsername(user.getName());

        this.view.setDifficulty((difficulty != null) ? difficulty : DifficultyEnum.EASY);
        this.view.setHealthComponent((healthComponent != null) ? healthComponent : HealthComponentEnum.CLASSIC);
        this.view.setNetworkSelected(network);
        this.view.reset();

        this.parentListener.selectMusic(MusicEnum.OPTIONS);
    }

    // Listeners

    @Override
    public void returnButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.MENU);
    }

    @Override
    public void playButtonPressed() {
        boolean formValidity = true;

        final String username = this.view.getUsername();
        final DifficultyEnum difficulty = this.view.getDifficulty();
        final HealthComponentEnum healthComponent = this.view.getHealthComponent();
        final String usernameMessage = this.validateUsernameField(username);
        final String difficultyMessage = this.validateDifficultyField(difficulty);
        final String healthComponentMessage = this.validateHealthComponentField(healthComponent);

        if (usernameMessage != null) {
            this.view.setUsernameInvalid(usernameMessage);
            formValidity = false;
        } else {
            this.view.setUsernameValid();
        }

        if (difficultyMessage != null) {
            this.view.setDifficultyInvalid(difficultyMessage);
            formValidity = false;
        } else {
            this.view.setDifficultyValid();
        }

        if (healthComponentMessage != null) {
            this.view.setHealthComponentInvalid(healthComponentMessage);
            formValidity = false;
        } else {
            this.view.setHealthComponentValid();
        }

        // If all fields are validated, set the data and show the game.
        if (formValidity) {
            this.gameModel.getOptions().setDifficulty(difficulty);
            this.gameModel.getOptions().setHealthComponent(healthComponent);

            // Get the user model, if the user doesn't exist yet, create it.
            UserModel userModel = this.getOrAddUser(username);
            this.gameModel.setUser(userModel);

            this.parentListener.selectPresenter(PresenterEnum.GAME);
        }
    }

    @Override
    public void networkTogglePressed() {
        this.setNetwork(!this.gameModel.getOptions().isNetworkEnabled());
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

    private String validateHealthComponentField(HealthComponentEnum healthComponent) {
        return null;
    }

    private UserModel getOrAddUser(String username) {
        try {
            this.userService.addUser(username);
        } catch (SQLException ignored) {}

        try {
            return this.userService.getUser(username);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new UserModel();
        }
    }

    private void setNetwork(boolean state) {
        this.gameModel.getOptions().setNetwork(state);
    }
}
