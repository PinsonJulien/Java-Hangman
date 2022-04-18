package com.jpinson.pendujfx.app.menu;

import com.jpinson.pendujfx.components.panes.constrainedGridPane.ConstrainedGridPane;
import com.jpinson.pendujfx.framework.view.View;
import com.jpinson.pendujfx.utils.CssClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MenuView
    extends View<ConstrainedGridPane, MenuViewListener>
    implements MenuViewListener
{
    private final Label gameTitleLabel = new Label("HANGMAN");
    private final Button quickPlayButton = new Button("QUICK PLAY");
    private final Button playButton = new Button("START");
    private final Button scoresButton = new Button("SCORES");
    private final Button quitButton = new Button("QUIT");

    public MenuView() {
        super(new ConstrainedGridPane());
        this.init();
    }

    // Getters / Setters

    // Interfaces
    @Override
    public void init() {
        this.pane.setId("menu");

        // Set actions
        this.quickPlayButton.setOnAction(this.quickPlayButtonHandler);
        this.playButton.setOnAction(this.startButtonHandler);
        this.scoresButton.setOnAction(this.menuButtonHandler);
        this.quitButton.setOnAction(this.quitButtonHandler);

        // Set placement
        this.pane.setColumns(100);
        this.pane.setRows(30, 70);

        // Game name panel
        VBox gameTitlePane = new VBox();
        CssClass.add(gameTitlePane, "titlePane");
        gameTitlePane.getChildren().add(this.gameTitleLabel);
        this.pane.add(gameTitlePane, 0, 0);

        // Buttons panel
        VBox buttonPane = new VBox();
        CssClass.add(buttonPane, "buttonPane");
        buttonPane.getChildren().addAll(
                this.quickPlayButton,
                this.playButton,
                this.scoresButton,
                this.quitButton
        );
        this.pane.add(buttonPane, 0, 1);

        // Hidden when starting the game.
        this.quickPlayButton.setVisible(false);
   }

    @Override
    public void reset() {}

    // Listeners

    @Override
    public void quickPlayButtonPressed() {
        for (MenuViewListener listener : getListeners()) {
            listener.quickPlayButtonPressed();
        }
    }

    @Override
    public void startButtonPressed() {
        for (MenuViewListener listener : getListeners()) {
            listener.startButtonPressed();
        }
    }

    @Override
    public void scoresButtonPressed() {
        for (MenuViewListener listener : getListeners()) {
            listener.scoresButtonPressed();
        }
    }

    @Override
    public void quitButtonPressed() {
        for (MenuViewListener listener : getListeners()) {
            listener.quitButtonPressed();
        }
    }

    // Events
    private final EventHandler<ActionEvent> quickPlayButtonHandler = actionEvent -> this.quickPlayButtonPressed();
    private final EventHandler<ActionEvent> startButtonHandler = actionEvent -> this.startButtonPressed();
    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> this.scoresButtonPressed();
    private final EventHandler<ActionEvent> quitButtonHandler = actionEvent -> this.quitButtonPressed();


    // Methods

    public void revealQuickPlayButton() {
        this.quickPlayButton.setVisible(true);
    }
}
