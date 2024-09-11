package com.example;

import com.example.ReadFromXMLFile.RetrieveXMLData;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Main {

//    private void transferdataToDatabase(ArrayList<Object> list, Integer id, String firstName, String lastName, String subject, String marks){
//        id = Integer.valueOf((String) list.get(0));
//        firstName = list.get()
//    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException, IOException, SAXException {
        Integer id;
        ArrayList<String> fillArrayListwithXml = RetrieveXMLData.getXMLdata();

//        System.out.println(fillArrayListwithXml.get(0));//id
        id = Integer.valueOf((String) fillArrayListwithXml.get(0));
        System.out.println(id);
        System.out.println(fillArrayListwithXml.get(1).getClass());//name
        System.out.println(fillArrayListwithXml.get(2));//lastname
        System.out.println(fillArrayListwithXml.get(3));//subject
        System.out.println(fillArrayListwithXml.get(4));//marks




        MysqlConnect mysqlConnect = new MysqlConnect();
        mysqlConnect.connect();

        //create new table named students in database
//        mysqlConnect.createTableStudents();
        //INSERT data into table students @USAGE: IN COMBINATION WITH XML METHOD
        mysqlConnect.insertIntoStudentsTable(
                id, fillArrayListwithXml.get(1), fillArrayListwithXml.get(2),
                fillArrayListwithXml.get(3), fillArrayListwithXml.get(4));


        readFromTableDatabase(mysqlConnect, "Students");
    }

    private static void readFromTableDatabase(MysqlConnect mysqlConnect, String tableName) {
        //read data from database, table students
        try {
            PreparedStatement statement = mysqlConnect.connect().prepareStatement("SELECT * FROM " + tableName +" ;");
            statement.execute();

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                //retrieve each id from table
                int id = rs.getInt("StudentID");
                //retrieve each String from table
                String name = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                String subject = rs.getString("Subject");
                String marks = rs.getString("Marks");
                //Print each ID and Name
                System.out.println("ID: " + id + ", Name: " + name +
                        ", First Name: " + firstName + ", Subject: " + subject
                + ", Marks: " + marks);
            }
            mysqlConnect.printTable(tableName);
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