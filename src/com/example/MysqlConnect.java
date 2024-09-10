package com.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class MysqlConnect {
    // init database constants
    public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1234";
    public static final String MAX_POOL = "250";

    public void printTable() throws SQLException {
        // Create a connection to the database
        Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        DBTablePrinter.printTable(conn, "Persons");
    }


    // init connection object
    private Connection connection;
    // init properties object
    private Properties properties;

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void createTablePersons() throws SQLException {
        String sqlCreate = "CREATE TABLE Persons (\n" +
                "    PersonID int,\n" +
                "    LastName varchar(255),\n" +
                "    FirstName varchar(255),\n" +
                "    Address varchar(255),\n" +
                "    City varchar(255)\n" +
                ");";

        Statement stmt = connection.createStatement();
        stmt.execute(sqlCreate);
    }

    public void insertIntoPersonsTable() throws SQLException {
        String sqlCreate = "INSERT INTO Persons (\n" +
                "  PersonID,LastName,FirstName,Address,City \n" +")"+
                "VALUES(007, 'James' ,'Bond', 'Aleea Dumbravita', 'Bucharest' )";

        Statement stmt = connection.createStatement();
        stmt.execute(sqlCreate);
    }
    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}