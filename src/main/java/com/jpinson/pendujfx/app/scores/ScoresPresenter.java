package com.jpinson.pendujfx.app.scores;

import com.jpinson.pendujfx.app.AppPresenterListener;
import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.enums.MusicEnum;
import com.jpinson.pendujfx.enums.PresenterEnum;
import com.jpinson.pendujfx.framework.presenter.ChildPresenter;
import com.jpinson.pendujfx.models.ScoreModel;
import com.jpinson.pendujfx.services.ScoreService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScoresPresenter
    extends ChildPresenter<AppPresenterListener, ScoresView>
    implements ScoresViewListener
{
    private final ScoreService scoreService;
    private DifficultyEnum selectedDifficulty = DifficultyEnum.EASY;

    public ScoresPresenter(
        ScoresView scoresView,
        AppPresenterListener listener,
        ScoreService scoreService
    ) {
        super(scoresView, listener);
        this.scoreService = scoreService;
        this.init();
    }

    // Interfaces
    @Override
    public void init() {}

    @Override
    public void reset() {
        this.refreshTable();
        this.parentListener.selectMusic(MusicEnum.SCORES);
    }

    // Listeners
    @Override
    public void menuButtonPressed() {
        this.parentListener.selectPresenter(PresenterEnum.MENU);
    }

    @Override
    public void difficultyComboBoxChanged() {
        this.selectedDifficulty = this.view.getDifficulty();
        this.refreshTable();
    }

    // Methods
    public void refreshTable () {
        try {
            ArrayList<ScoreModel> scores = this.scoreService.getTotalScores(
                    10,
                    this.selectedDifficulty
            );

            this.view.reset();
            this.view.insertRows(scores);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
