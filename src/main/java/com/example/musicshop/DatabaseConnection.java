package com.example.musicshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    public static Connection getConnection() throws SQLException {
        String databaseName = "music_shop";
        String databaseUser = "root";
        String databasePassword = "conversation";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
//        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        return databaseLink;
    }

}
