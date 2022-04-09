package com.jpinson.pendujfx;

import com.jpinson.pendujfx.app.AppPresenter;
import com.jpinson.pendujfx.enums.PresenterEnum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    private final String viewFileName = "app.fxml";
    private final String styleFileName = "app.css";

    public App() {}

    public static void main(String[] args) {
        new App();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        AppPresenter appPresenter = new AppPresenter();

        //AlphabeticKeyboard kb = view.getKeyboard();
        //kb.getKeys().get('A').setDisable(true);

        // Show the window
        //Scene scene = new Scene(mainPresenter.getView().getPane(), 320, 240);
        Scene scene = new Scene(appPresenter.getView().getPane(), 320, 240);
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
