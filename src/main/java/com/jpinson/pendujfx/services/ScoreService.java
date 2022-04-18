package com.jpinson.pendujfx.services;

import com.jpinson.pendujfx.enums.DifficultyEnum;
import com.jpinson.pendujfx.framework.service.Service;
import com.jpinson.pendujfx.models.ScoreModel;
import com.jpinson.pendujfx.models.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ScoreService extends Service<ScoreModel> {
    private final UserService userService = new UserService();

    @Override
    protected ScoreModel generateModel(ResultSet rs) throws SQLException {
        return new ScoreModel(
            rs.getInt("score_id"),
            rs.getInt("score_score"),
            DifficultyEnum.valueOf(rs.getString("score_difficulty")),
            this.userService.generateModel(rs)
        );
    }

    public void addScore (
        int score,
        DifficultyEnum difficulty,
        UserModel user
    ) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("""
            INSERT INTO scores
                (score, difficulty, user_id)
            VALUES
                (?, ?, ?);
        """);
        statement.setInt(1, score);
        statement.setString(2, difficulty.toString());
        statement.setInt(3, user.getId());
        statement.executeUpdate();
    }

    public ArrayList<ScoreModel> getScores() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("""
            SELECT
                S.id AS 'score_id',
                S.score AS 'score_score',
                S.difficulty AS 'score_difficulty',
                U.id AS 'user_id',
                U.name AS 'user_name'
            FROM scores S
            INNER JOIN users U
                ON U.id = S.user_id
            ORDER BY
                S.score DESC,
                U.name ASC,
                S.difficulty ASC
            ;
        """);

        return this.generateModelList(rs);
    }

    public ArrayList<ScoreModel> getTotalScores(int limit, DifficultyEnum difficulty) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("""
            SELECT
                S.id AS 'score_id',
                SUM(S.score) AS 'score_score',
                S.difficulty AS 'score_difficulty',
                U.id AS 'user_id',
                U.name AS 'user_name'
            FROM scores S
            INNER JOIN users U
                ON U.id = S.user_id
            WHERE
                S.difficulty = ?
            GROUP BY
                U.id
            ORDER BY
                score_score DESC,
                U.name ASC
            LIMIT ?
            ;
        """);
        statement.setString(1, difficulty.toString());
        statement.setInt(2, limit);
        ResultSet rs = statement.executeQuery();

        return this.generateModelList(rs);
    }
}
