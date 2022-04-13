package com.jpinson.pendujfx.services;

import com.jpinson.pendujfx.framework.service.Service;
import com.jpinson.pendujfx.models.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserService extends Service<UserModel> {
    @Override
    protected UserModel generateModel(ResultSet rs) throws SQLException {
        return new UserModel(
            rs.getInt("user_id"),
            rs.getString("user_name")
        );
    }

    public void addUser (
        String name
    ) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("""
            INSERT INTO users
                (name)
            VALUES
                (?);
        """);

        statement.setString(1, name);
        statement.executeUpdate();
    }

    public UserModel getUser(
        int id
    ) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("""
            SELECT
                id AS 'user_id',
                name AS 'user_name'
            FROM users
            WHERE
                id = ?;
        """);

        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return this.generateModel(rs);
    }

    public UserModel getUser (
        String name
    ) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement("""
            SELECT
                id AS 'user_id',
                name AS 'user_name'
            FROM users
            WHERE
                name = ?
        """);
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        rs.next();
        return this.generateModel(rs);
    }

    public ArrayList<UserModel> getUsers () throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet rs = statement.executeQuery("""
            SELECT
                id AS 'user_id',
                name AS 'user_name'
            FROM users
            ORDER BY name ASC;
        """);

        return this.generateModelList(rs);
    }
}