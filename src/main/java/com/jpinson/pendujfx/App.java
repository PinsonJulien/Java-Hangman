package com.jpinson.pendujfx;

import com.jpinson.pendujfx.app.AppPresenter;
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
        AppPresenter appPresenter = new AppPresenter();

        // Show the window
        Scene scene = new Scene(appPresenter.getView().getPane(), 320, 240);
        final URL style = this.getClass().getResource(this.styleFileName);
        scene.getStylesheets().add(style.toString());
        stage.setScene(scene);
        stage.show();
    }

}
