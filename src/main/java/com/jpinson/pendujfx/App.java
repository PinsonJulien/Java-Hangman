package com.jpinson.pendujfx;

import com.jpinson.pendujfx.app.AppPresenter;
import com.jpinson.pendujfx.app.AppView;
import com.jpinson.pendujfx.app.game.GamePresenter;
import com.jpinson.pendujfx.app.game.GameView;
import com.jpinson.pendujfx.app.gameOver.GameOverPresenter;
import com.jpinson.pendujfx.app.gameOver.GameOverView;
import com.jpinson.pendujfx.app.menu.MenuPresenter;
import com.jpinson.pendujfx.app.menu.MenuView;
import com.jpinson.pendujfx.app.options.OptionsPresenter;
import com.jpinson.pendujfx.app.options.OptionsView;
import com.jpinson.pendujfx.app.scores.ScoresPresenter;
import com.jpinson.pendujfx.app.scores.ScoresView;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.models.OptionsModel;
import com.jpinson.pendujfx.services.ScoreService;
import com.jpinson.pendujfx.services.UserService;
import com.jpinson.pendujfx.services.WordService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class App extends Application {
    final AppPresenter appPresenter;

    public App() {
        // Generate the main presenter
        this.appPresenter = new AppPresenter(
            new AppView()
        );

        this.init();
    }

    public static void main(String[] args) {
        new App();
        launch();
    }

    public void init() {
        // Generate models and default values
        OptionsModel optionsModel = new OptionsModel();
        optionsModel.setDifficulty(DifficultyEnum.EASY);
        optionsModel.setEncryptingCharacter('?');

        GameModel gameModel = new GameModel(optionsModel);
        gameModel.setMaxHealth(6);

        // Generate services
        ScoreService scoreService = new ScoreService();
        UserService userService = new UserService();
        WordService wordService = new WordService();

        // Generate all child views / presenters.
        MenuView menuView = new MenuView();
        MenuPresenter menuPresenter = new MenuPresenter(
            menuView,
            this.appPresenter,
            gameModel
        );
        menuView.addListener(menuPresenter);

        OptionsView optionsView = new OptionsView();
        OptionsPresenter optionsPresenter = new OptionsPresenter(
            optionsView,
            this.appPresenter,
            gameModel,
            userService
        );
        optionsView.addListener(optionsPresenter);

        GameView gameView = new GameView();
        GamePresenter gamePresenter = new GamePresenter(
            gameView,
            this.appPresenter,
            gameModel,
            wordService,
            scoreService
        );
        gameView.addListener(gamePresenter);

        GameOverView gameOverView = new GameOverView();
        GameOverPresenter gameOverPresenter = new GameOverPresenter(
            gameOverView,
            this.appPresenter,
            gameModel
        );
        gameOverView.addListener(gameOverPresenter);

        ScoresView scoresView = new ScoresView();
        ScoresPresenter scoresPresenter = new ScoresPresenter(
            scoresView,
            this.appPresenter,
            scoreService
        );
        scoresView.addListener(scoresPresenter);

        // Adding all child presenters to the parent one.
        this.appPresenter.addChildPresenter(PresenterEnum.MENU, menuPresenter);
        this.appPresenter.addChildPresenter(PresenterEnum.OPTIONS, optionsPresenter);
        this.appPresenter.addChildPresenter(PresenterEnum.GAME, gamePresenter);
        this.appPresenter.addChildPresenter(PresenterEnum.GAMEOVER, gameOverPresenter);
        this.appPresenter.addChildPresenter(PresenterEnum.SCORES, scoresPresenter);

        // Setup the first visible presenter
        appPresenter.changeView(PresenterEnum.MENU);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Setup scene
        Scene scene = new Scene(this.appPresenter.getView().getPane(), 896, 414);

        // Setup style
        final URL style = this.getClass().getResource("app.css");
        if (style != null ) scene.getStylesheets().add(style.toString());

        // remove top bar
        stage.initStyle(StageStyle.UNDECORATED);

        // Show
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
