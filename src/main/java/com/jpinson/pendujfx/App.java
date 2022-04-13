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
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.models.GameModel;
import com.jpinson.pendujfx.models.OptionsModel;
import com.jpinson.pendujfx.models.UserModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    public App() {}

    public static void main(String[] args) {
        new App();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Generate all models
        GameModel gameModel = new GameModel();
        OptionsModel optionsModel = new OptionsModel();
        UserModel userModel = new UserModel();

        // Generate the main presenter, and all child views / presenters.
        AppView appView = new AppView();
        AppPresenter appPresenter = new AppPresenter(appView);

        MenuView menuView = new MenuView();
        MenuPresenter menuPresenter = new MenuPresenter(menuView, appPresenter);
        menuView.addListener(menuPresenter);

        OptionsView optionsView = new OptionsView();
        OptionsPresenter optionsPresenter = new OptionsPresenter(optionsView, appPresenter, optionsModel, userModel);
        optionsView.addListener(optionsPresenter);

        GameView gameView = new GameView();
        GamePresenter gamePresenter = new GamePresenter(gameView, appPresenter, gameModel, optionsModel, userModel);
        gameView.addListener(gamePresenter);

        GameOverView gameOverView = new GameOverView();
        GameOverPresenter gameOverPresenter = new GameOverPresenter(gameOverView, appPresenter);
        gameOverView.addListener(gameOverPresenter);

        // Adding all child presenters to the parent one.
        appPresenter.addChildPresenter(PresenterEnum.MENU, menuPresenter);
        appPresenter.addChildPresenter(PresenterEnum.OPTIONS, optionsPresenter);
        appPresenter.addChildPresenter(PresenterEnum.GAME, gamePresenter);
        appPresenter.addChildPresenter(PresenterEnum.GAMEOVER, gameOverPresenter);

        // Setup the first visible presenter
        appPresenter.changeView(PresenterEnum.MENU);

        // Setup scene
        Scene scene = new Scene(appPresenter.getView().getPane(), 320, 240);

        // Setup style
        final URL style = this.getClass().getResource("app.css");
        if (style != null ) scene.getStylesheets().add(style.toString());

        // Show
        stage.setScene(scene);
        stage.show();
    }

}
