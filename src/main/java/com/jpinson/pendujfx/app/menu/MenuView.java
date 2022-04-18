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
    private final Button playButton = new Button("START");
    private final Button scoresButton = new Button("SCORES");

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
        this.playButton.setOnAction(this.newGameButtonHandler);
        this.scoresButton.setOnAction(this.menuButtonHandler);

        // Set placement
        this.pane.setColumns(100);
        this.pane.setRows(50, 50);

        // Game name panel
        VBox gameTitlePane = new VBox();
        CssClass.add(gameTitlePane, "titlePane");
        gameTitlePane.getChildren().add(this.gameTitleLabel);
        this.pane.add(gameTitlePane, 0, 0);

        // Buttons panel
        VBox buttonPane = new VBox();
        CssClass.add(buttonPane, "buttonPane");
        buttonPane.getChildren().addAll(
                this.playButton,
                this.scoresButton
        );
        this.pane.add(buttonPane, 0, 1);
    }

    @Override
    public void reset() {}

    // Listeners

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

    // Events
    private final EventHandler<ActionEvent> newGameButtonHandler = actionEvent -> this.startButtonPressed();
    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> this.scoresButtonPressed();

    // Methods
}
