package com.jpinson.pendujfx;

import com.jpinson.pendujfx.app.AppPresenter;
import com.jpinson.pendujfx.config.Config;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class App extends Application {
    private final String styleFileName = "app.css";

    public App() {}

    public static void main(String[] args) {
        new App();
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        String test = new Config().getDicolinkKey();
        System.out.println(test);

        AppPresenter appPresenter = new AppPresenter();

        // Setup scene
        Scene scene = new Scene(appPresenter.getView().getPane(), 320, 240);

        // Setup style
        final URL style = this.getClass().getResource(this.styleFileName);
        scene.getStylesheets().add(style.toString());

        // Show
        stage.setScene(scene);
        stage.show();
    }

}
