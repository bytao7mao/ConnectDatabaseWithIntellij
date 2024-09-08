package com.example;


import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class Main {
    String s = this.getClass().getCanonicalName();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        getConnection();
        System.out.println("Hello world!");
//        com.example.Main s = new com.example.Main();
//        System.out.println(s.s);

        // !_ note _! this is just init
// it will not create a connection
        MysqlConnect mysqlConnect = new MysqlConnect();
        mysqlConnect.connect();

//        mysqlConnect.createTable();
        mysqlConnect.insertIntoPersonsTable();
        String sql = "SELECT * FROM Persons;";
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);
            statement.execute();
            System.out.println(statement.execute());

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1)); //gets the first column's rows.
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("columns: "+rsmd.getColumnCount());
            System.out.println("Column Name of 1st column: "+rsmd.getColumnName(1));
            System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(1));
            System.out.println("" );
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for(int i = 1; i < columnsNumber; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String d = "C:\\Users\\tao\\Desktop\\mysql-connector-j-9.0.0 ";
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
            String url = "jdbc:mysql://localhost:3306/mysql";
            String username = "root";
            String password = "1234";

            System.out.println("Connecting database ...");

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Database connected!");
            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }

//            MysqlDataSource dataSource = new MysqlDataSource();
//            dataSource.setUser("root");
//            dataSource.setPassword("1234");
//            dataSource.setServerName("jdbc:mysql://localhost:3306/world");
//            Connection connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(
//                    "use world;\n" +
//                        "select * from country;");
//            System.out.println(resultSet.toString());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

}