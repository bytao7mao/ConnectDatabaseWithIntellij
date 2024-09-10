package com.example;


import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

import static com.example.MysqlConnect.*;

public class Main {
    String s = this.getClass().getCanonicalName();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        MysqlConnect mysqlConnect = new MysqlConnect();
        mysqlConnect.connect();

        String query = "SELECT * FROM Persons;";
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement(query);
            statement.execute();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("PersonID");
                String name = rs.getString("LastName");
                System.out.println("ID: " + id + ", Name: " + name);
            }
            mysqlConnect.printTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }

//            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
//            Statement st = conn.createStatement();
//            ResultSet resultSet = st.executeQuery(query);
//            ResultSetMetaData rsmd = resultSet.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while (resultSet.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    System.out.print(resultSet.getString(i) + " "); //Print one element of a row
//                }
//                System.out.println();//Move to the next line to print the next row.
//            }





}