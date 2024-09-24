package com.example;

import com.example.Util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.example.MysqlConnect.*;

public class StudentDAO extends DataAccessObject<Student> {

    private static final String GET_ONE = "SELECT StudentID, FirstName, LastName, " +
            "SUBJECT, MARKS FROM students WHERE StudentID = ?;";

    private static final String INSERT =
            "INSERT INTO " + STUDENTTABLE + " (\n" +
                    " " + STUDENT_ID + ","
                    + FIRST_NAME + ","
                    + LAST_NAME + ","
                    + SUBJECT + ","
                    + MARKS + " \n" +")"+
                    "VALUES(?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE " + STUDENTTABLE + " SET " +
            FIRST_NAME + " = ?," +
            LAST_NAME + " = ?," +
            SUBJECT + " = ?," +
            MARKS + " = ?" + " WHERE " + STUDENT_ID + " = ?;";
    public static final String LASTNAMESTUDENTFROMTABLE = STUDENTTABLE+"."+LAST_NAME;
    public static final String IDSTUDENTFROMTABLE = STUDENTTABLE + "." + STUDENT_ID;
    public static final String FIRSTNAMEFROMTABLE = STUDENTTABLE + "."+FIRST_NAME;
    public static final String SUBJECTFROMTABLE = STUDENTTABLE + "."+SUBJECT;
    public static final String MARKSFROMTABLE = STUDENTTABLE + "."+MARKS;


    //-- OFFSET 0 LAST VALUE
    public static final String LASTVALFROMTABLE = "SELECT " + IDSTUDENTFROMTABLE +
            ", " + FIRSTNAMEFROMTABLE + ", " + LASTNAMESTUDENTFROMTABLE + ", " +
            SUBJECTFROMTABLE + ", " + MARKSFROMTABLE +
            " FROM " + STUDENTTABLE + " " +
            " ORDER BY " + IDSTUDENTFROMTABLE
            + " DESC LIMIT 1 OFFSET 0;";





    public StudentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Student findById(long id) {
        Student student = new Student();
        try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE)){
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                student.setId(rs.getInt(STUDENT_ID));
                student.setFirstName(rs.getString(FIRST_NAME));
                student.setLastName(rs.getString(LAST_NAME));
                student.setSubject(rs.getString(SUBJECT));
                student.setMarks(rs.getString(MARKS));
            }
        }catch (SQLException e){e.printStackTrace();throw new RuntimeException();}
        return student;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student update(Student dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);){
            activateSafeUpdates();
            prepareUpdateStatementForStudentObject(dto, statement);
        }catch (SQLException e){e.printStackTrace();throw new RuntimeException(e);}
        return null;
    }




//    public Student readLastValueFromDB() {
//        try(PreparedStatement statement = this.connection.prepareStatement(LASTVALFROMTABLE)){
//            activateSafeUpdates();
//            statement.execute();
//        }catch (SQLException e){e.printStackTrace();throw new RuntimeException(e);}
//        return null;
//    }

    public Student update(Student dto, String firstName, String lastName, String subject, String marks){
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE)){
            activateSafeUpdates();
            prepareUpdateStatementForStudentObjectUpdateFirstName(dto, statement, firstName, lastName, subject, marks);
        }catch (SQLException e){e.printStackTrace();throw new RuntimeException(e);}
        return null;
    }

    @Override
    public Student create(Student dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT);){
            prepareInsertStatementForStudentObject(dto, statement);
        }catch (SQLException e){e.printStackTrace();throw new RuntimeException(e);}
        return null;
    }

    @Override
    public void delete(long id) {

    }

    private void prepareInsertStatementForStudentObject(Student s, PreparedStatement statement) throws SQLException {
        statement.setLong(1, s.getId());
        statement.setString(2,s.getFirstName());
        statement.setString(3,s.getLastName());
        statement.setString(4,s.getSubject());
        statement.setString(5,s.getMarks());
        statement.execute();
    }
    private void prepareUpdateStatementForStudentObjectUpdateFirstName(Student s,PreparedStatement statement,
                                                        String updateFirstName, String updateLastName,
                                                                       String updateSubject, String updateMarks) throws SQLException {
        statement.setString(1,updateFirstName);
        statement.setString(2,updateLastName);
        statement.setString(3,updateSubject);
        statement.setString(4,updateMarks);
        statement.setInt(5, s.getId());
        statement.execute();
    }
    private void prepareUpdateStatementForStudentObject(Student s,PreparedStatement statement) throws SQLException {
        statement.setString(1,s.getFirstName());
        statement.setString(2,s.getLastName());
        statement.setString(3,s.getSubject());
        statement.setString(4,s.getMarks());
        statement.setInt(5, s.getId());
        statement.execute();
    }
    private void prepareUpdateStatementForStudentObjectUpdateLastName(Student s,PreparedStatement statement,
                                                        String updateLastName) throws SQLException {
        statement.setString(1,s.getFirstName());
        statement.setString(2,updateLastName);
        statement.setString(3,s.getSubject());
        statement.setString(4,s.getMarks());
        statement.setInt(5, s.getId());
        statement.execute();
    }
    private void activateSafeUpdates(){
        String select = "SET SQL_SAFE_UPDATES = 0;";
        try {
            PreparedStatement statement = this.connection.prepareStatement(select);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
