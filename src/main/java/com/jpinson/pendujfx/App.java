package com.jpinson.pendujfx;

import com.jpinson.pendujfx.components.layout.Layout;
import com.jpinson.pendujfx.presenters.MainPresenter;
import com.jpinson.pendujfx.views.View;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private final String viewFileName = "app.fxml";
    private final String styleFileName = "app.css";
    private final Layout layout;

    //private final MenuController menuController;
    //private final GameController gameController;

    public App() {
        this.layout = new Layout();
        //this.menuController = new MenuController();
        //this.gameController = new GameController();
    }

    public static void main(String[] args) {
        new App();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Insert menu view inside the layout

        MainPresenter mainPresenter = new MainPresenter();


        //AlphabeticKeyboard kb = view.getKeyboard();
        //kb.getKeys().get('A').setDisable(true);

        // Show the window
        Scene scene = new Scene(mainPresenter.getView().getPane(), 320, 240);
        stage.setScene(scene);
        stage.show();

        /*final URL view = this.getClass().getResource(this.viewFileName);
        final URL style = this.getClass().getResource(this.styleFileName);

        FXMLLoader fxmlLoader = new FXMLLoader(view);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(style.toString());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/
    }

}
