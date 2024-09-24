package com.example;

import com.example.ReadFromXMLFile.RetrieveXMLData;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.MysqlConnect.*;
import static com.example.StudentDAO.LASTVALFROMTABLE;

public class Main {
    public static final String TABLE_NAME = "students";
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException, IOException, SAXException {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Please input your path file, location of your xml file:\n");
//        ArrayList<Student> studentArrayList = RetrieveXMLData.getXMLdata(new File(sc.nextLine()));
//        System.out.println("What operation do you request me to do ?\n" +
//                "1.CREATE\n" +
//                "2.READ\n" +
//                "3.UPDATE\n" +
//                "4.DELETE\n");


        System.out.println();


        MysqlConnect mysqlConnect = new MysqlConnect();
        mysqlConnect.connect();

//        String answerFromUser = sc.nextLine();
//        if (answerFromUser.equalsIgnoreCase("create") || answerFromUser.equalsIgnoreCase("1")){
//            mysqlConnect.createTableStudents();
//        } if (answerFromUser.equalsIgnoreCase("read") || answerFromUser.equalsIgnoreCase("2")){
//            readFromTableDatabase(mysqlConnect, TABLE_NAME);
//        } else {
//            System.out.println("Start the program again, unexpected error!");
//            System.exit(0);
//        }
        //CREATE new table named students in database
//        mysqlConnect.createTableStudents();
        //INSERT data into table students @USAGE: IN COMBINATION WITH XML METHOD

//        for (Student s:studentArrayList) {
////            System.out.println(s.getFirstName());
////
////            System.out.println(s.getMarks());
////
////
//            //INSERT data into students TABLE DB
//            mysqlConnect.insertIntoStudentsTable(
//                    s.getId(), s.getFirstName(), s.getLastName(),
//                    s.getSubject(), s.getMarks());
//        }

//        insertStudentsIntoTableUsingDAOandDTOPattern(studentArrayList);


        //delete
//        mysqlConnect.deleteDuplicate();

//        updateStudentsIntoTableUsingDAOandDTOPattern(102, "GORILLA");

        readLastValue(mysqlConnect, TABLE_NAME);

        mysqlConnect.printTable(TABLE_NAME);
//        readFromTableDatabase(mysqlConnect, TABLE_NAME);
    }



    private static void insertStudentsIntoTableUsingDAOandDTOPattern(ArrayList<Student> studentArrayList) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager(USERNAME, PASSWORD);
        try {
            Connection connection = dcm.getConnection();
            StudentDAO studentDAO = new StudentDAO(connection);
            for (Student s: studentArrayList) {
                //create new student from info xml file and
                //makes an insert into database table
                studentDAO.create(s);
            }
        } catch (SQLException e){e.printStackTrace();}
    }
    private static void updateStudentsIntoTableUsingDAOandDTOPattern(int id, String firstName) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager(USERNAME, PASSWORD);
        try {
            Connection connection = dcm.getConnection();
            StudentDAO studentDAO = new StudentDAO(connection);
            Student getStudentById = studentDAO.findById(id);
            studentDAO.update(getStudentById, firstName);
        } catch (SQLException e){e.printStackTrace();}
    }


    private static ArrayList<Student> readFromTableDatabase(MysqlConnect mysqlConnect, String tableName) {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        //read data from database, table students
        try {
            PreparedStatement statement =
                    mysqlConnect.connect().
                            prepareStatement(
                                    "SELECT * FROM students");


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                //transfering into POJO
                studentArrayList.add(new Student(rs.getInt(MysqlConnect.STUDENT_ID),
                        rs.getString(MysqlConnect.FIRST_NAME),
                        rs.getString(MysqlConnect.LAST_NAME),
                        rs.getString(MysqlConnect.SUBJECT),
                        rs.getString(MysqlConnect.MARKS)));

                //retrieve each id from table
//                int id = rs.getInt(MysqlConnect.STUDENT_ID);
//                //retrieve each String from table
//                String lastName = rs.getString(MysqlConnect.LAST_NAME);
//                String firstName = rs.getString(MysqlConnect.FIRST_NAME);
//                String subject = rs.getString(MysqlConnect.SUBJECT);
//                String marks = rs.getString(MysqlConnect.MARKS);
                //Print each ID and Name
            }
            for (Student s:studentArrayList) {
                System.out.println("ID: " + s.getId() + ", Name: " + s.getFirstName() +
                        ", First Name: " + s.getLastName() + ", Subject: " + s.getSubject()
                        + ", Marks: " + s.getMarks());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        return studentArrayList;
    }

    private static void readLastValue(MysqlConnect mysqlConnect, String tableName) throws SQLException {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        //read data from database, table students
        try {
            PreparedStatement statement =
                    mysqlConnect.connect().
                            prepareStatement(
                                    LASTVALFROMTABLE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                //transfering into POJO
                studentArrayList.add(new Student(rs.getInt(MysqlConnect.STUDENT_ID),
                        rs.getString(MysqlConnect.FIRST_NAME),
                        rs.getString(MysqlConnect.LAST_NAME),
                        rs.getString(MysqlConnect.SUBJECT),
                        rs.getString(MysqlConnect.MARKS)));
                //retrieve each id from table
//                int id = rs.getInt(MysqlConnect.STUDENT_ID);
//                //retrieve each String from table
//                String lastName = rs.getString(MysqlConnect.LAST_NAME);
//                String firstName = rs.getString(MysqlConnect.FIRST_NAME);
//                String subject = rs.getString(MysqlConnect.SUBJECT);
//                String marks = rs.getString(MysqlConnect.MARKS);
                //Print each ID and Name
            }
            for (Student s:studentArrayList) {
                System.out.println("ID: " + s.getId() + ", Name: " + s.getFirstName() +
                        ", First Name: " + s.getLastName() + ", Subject: " + s.getSubject()
                        + ", Marks: " + s.getMarks());
            }
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