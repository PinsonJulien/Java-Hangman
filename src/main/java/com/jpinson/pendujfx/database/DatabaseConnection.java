package com.jpinson.pendujfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection {
    private static final String url = "jdbc:sqlite:PenduDB";
    private static final Connection connection;

    static {
        Connection c = null;
        try {
            c = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connection = c;
    }

    public static Connection getConnection() {
        return connection;
    }
}
