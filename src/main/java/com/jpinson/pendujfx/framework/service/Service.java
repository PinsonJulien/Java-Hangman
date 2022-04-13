package com.jpinson.pendujfx.framework.service;

import com.jpinson.pendujfx.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Services hold database operations to avoid clutter of presenters.
public abstract class Service<T> {
    protected final Connection connection = DatabaseConnection.getConnection();

    // Generates a model from a set of data
    protected abstract T generateModel(ResultSet rs) throws SQLException;

    // Generate a list using the overrided generateModel.
    protected ArrayList<T> generateModelList(ResultSet rs) throws SQLException {
        ArrayList<T> list = new ArrayList<>();
        while (rs.next()) {
            list.add(this.generateModel(rs));
        }
        return list;
    }
}
