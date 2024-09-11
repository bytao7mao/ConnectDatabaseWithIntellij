package com.example;


import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

import static com.example.MysqlConnect.*;

public class Main {
    String s = this.getClass().getCanonicalName();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        MysqlConnect mysqlConnect = new MysqlConnect();
        mysqlConnect.connect();

        //create new table named students in database
//        mysqlConnect.createTableStudents();
        //INSERT data into table students
//        mysqlConnect.insertIntoStudentsTable(
//                1, "Marius", "Nicolae",
//                "Teza de doctorat", "10");
        //read data from database, table students


        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement("SELECT * FROM Students;");
            statement.execute();

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                //retrieve each id from table
                int id = rs.getInt("StudentID");
                //retrieve each String from table
                String name = rs.getString("LastName");
                //Print each ID and Name
                System.out.println("ID: " + id + ", Name: " + name);
            }
            mysqlConnect.printTable("Students");
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