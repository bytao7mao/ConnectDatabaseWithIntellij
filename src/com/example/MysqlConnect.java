package com.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class MysqlConnect {
    // init database constants
    public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1234";
    public static final String MAX_POOL = "250";

    public void printTable(String tableName) throws SQLException {
        // Create a connection to the database
        Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        DBTablePrinter.printTable(conn, tableName);
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

    public void createTableStudents() throws SQLException {
        String sqlCreate = "CREATE TABLE Students (\n" +
                "    StudentID int,\n" +
                "    LastName varchar(255),\n" +
                "    FirstName varchar(255),\n" +
                "    Subject varchar(255),\n" +
                "    Marks varchar(255)\n" +
                ");";

        Statement stmt = connection.createStatement();
        stmt.execute(sqlCreate);
        connect();
    }

    public void insertIntoStudentsTable(Integer id,
                                        String lastName,
                                        String firstName,
                                        String subject,
                                        String marks) throws SQLException {
        connect();
        String sqlCreate = "INSERT INTO Students (\n" +
                " StudentID,LastName,FirstName,Subject,Marks \n" +")"+
                "VALUES(?, ?, ?, ?, ?)";
        // Prepare Statement
        PreparedStatement statement = this.connection.prepareStatement(sqlCreate);
        statement.setInt(1, id);
        statement.setString(2,lastName);
        statement.setString(3,firstName);
        statement.setString(4,subject);
        statement.setString(5,marks);
        statement.execute();
        disconnect();

        //Statement stmt = connection.createStatement();
        //stmt.execute(sqlCreate);
    }

    public void createTablePersons() throws SQLException {
        connect();
        String sqlCreate = "CREATE TABLE Persons (\n" +
                "    PersonID int,\n" +
                "    LastName varchar(255),\n" +
                "    FirstName varchar(255),\n" +
                "    Address varchar(255),\n" +
                "    City varchar(255)\n" +
                ");";

        Statement stmt = connection.createStatement();
        stmt.execute(sqlCreate);
        disconnect();
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