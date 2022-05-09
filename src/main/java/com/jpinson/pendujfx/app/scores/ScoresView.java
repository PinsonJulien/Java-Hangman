package com.jpinson.pendujfx.app.scores;

import com.jpinson.pendujfx.components.form.field.comboBox.DifficultyComboBox;
import com.jpinson.pendujfx.components.pane.ConstrainedGridPane;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.framework.view.View;
import com.jpinson.pendujfx.models.ScoreModel;
import com.jpinson.pendujfx.utils.CssClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class ScoresView
    extends View<ConstrainedGridPane, ScoresViewListener>
    implements ScoresViewListener
{
    private final Label titleLabel = new Label("TOP 10 PLAYERS");
    private final Button menuButton = new Button("RETURN TO MENU");
    private final DifficultyComboBox difficultyComboBox = new DifficultyComboBox(DifficultyEnum.EASY);
    private final ConstrainedGridPane scoreGrid = new ConstrainedGridPane();

    public ScoresView () {
        super(new ConstrainedGridPane());
        this.init();
    }

    // Interfaces
    @Override
    public void init() {
        this.pane.setId("scores");

        // set actions
        this.menuButton.setOnAction(this.menuButtonHandler);
        this.difficultyComboBox.setOnAction(this.comboBoxHandler);

        this.pane.setColumns(30, 40, 30);
        this.pane.setRows(15, 80, 5);

        // Title grid
        VBox buttonPane = new VBox();
        CssClass.add(buttonPane, "button-pane");
        buttonPane.getChildren().add(this.menuButton);
        this.pane.add(buttonPane, 0,0);

        VBox titlePane = new VBox();
        CssClass.add(titlePane, "title-pane");
        titlePane.getChildren().add(this.titleLabel);
        this.pane.add(titlePane, 1,0);

        VBox comboBoxPane = new VBox();
        CssClass.add(comboBoxPane, "combo-box-pane");
        comboBoxPane.getChildren().add(this.difficultyComboBox);
        this.pane.add(comboBoxPane, 2, 0);

        // Score grid
        CssClass.add(this.scoreGrid, "score-grid");
        this.scoreGrid.setColumns(50, 50);
        // table header + 10 rows
        this.scoreGrid.setRows(10, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9);
        this.pane.add(this.scoreGrid, 1, 1);
    }

    @Override
    public void reset() {
        // reset the score grid.
        this.removeRows();
        this.insertHeader();
    }

    // Listeners
    @Override
    public void menuButtonPressed() {
        for (ScoresViewListener listener : getListeners()) {
            listener.menuButtonPressed();
        }
    }

    @Override
    public void difficultyComboBoxChanged() {
        for (ScoresViewListener listener : getListeners()) {
            listener.difficultyComboBoxChanged();
        }
    }

    // Events
    private final EventHandler<ActionEvent> menuButtonHandler = actionEvent -> this.menuButtonPressed();
    private final EventHandler<ActionEvent> comboBoxHandler = actionEvent -> this.difficultyComboBoxChanged();

    // Methods
    public DifficultyEnum getDifficulty() {
        return this.difficultyComboBox.getValue();
    }

    private void insertHeader() {
        VBox scoreGridUsername = new VBox();
        CssClass.add(scoreGridUsername, "header");
        scoreGridUsername.getChildren().add(
            new Label("Username")
        );
        this.scoreGrid.add(scoreGridUsername, 0, 0);

        VBox scoreGridScore = new VBox();
        CssClass.add(scoreGridScore, "header");
        scoreGridScore.getChildren().add(
            new Label("Score")
        );
        this.scoreGrid.add(scoreGridScore, 1, 0);
    }

    public void insertRows(ArrayList<ScoreModel> scoreModels) {
        int len = scoreModels.size();
        for (int i = 0; i < len ; ++i) {
            this.insertRow(scoreModels.get(i), i + 1);
        }
    }

    private void insertRow(ScoreModel scoreModel, int row) {
        String username = scoreModel.getUser().getName();
        String score = String.valueOf(scoreModel.getScore());

        VBox usernamePane = new VBox();
        CssClass.add(usernamePane, "cell");
        usernamePane.getChildren().add(
            new Label(username)
        );

        VBox scorePane = new VBox();
        CssClass.add(scorePane, "cell");
        scorePane.getChildren().add(
            new Label(score)
        );

        this.scoreGrid.add(usernamePane, 0, row);
        this.scoreGrid.add(scorePane, 1, row);
    }

    public void removeRows() {
        this.scoreGrid.getChildren().clear();
    }
}
