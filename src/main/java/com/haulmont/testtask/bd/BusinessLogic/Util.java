package com.haulmont.testtask.bd.BusinessLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    protected static final String DB_DRIVERS = "org.hsqldb.jdbcDriver";
    protected static final String DB_URL = "jdbc:hsqldb:file:/C:/Users/chizhov/Desktop/haulmont/test-task-master/dbpath/MyDB";
    protected static final String DB_USERNAME = "SA";
    protected static final String DB_PASSWORD = "";
    protected static Connection connection = null;

    protected static void openConnection() {
        try {
            Class.forName(DB_DRIVERS);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("CONNECTION YES");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("CONNECTION ERROR");

        }

    }

    public static Connection connection() {
        if (connection != null) {
            return connection;
        } else {
            openConnection();
            return connection;
        }
    }
}