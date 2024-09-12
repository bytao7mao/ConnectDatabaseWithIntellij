package com.example;

import com.example.Util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.example.MysqlConnect.*;

public class StudentDAO extends DataAccessObject<Student> {

    public static final String INSERT =
            "INSERT INTO " + STUDENTTABLE + " (\n" +
                    " " + STUDENT_ID + ","
                    + LAST_NAME + ","
                    + FIRST_NAME + ","
                    + SUBJECT + ","
                    + MARKS + " \n" +")"+
                    "VALUES(?, ?, ?, ?, ?)";

    public StudentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Student findById(long id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student update(Student dto) {
        return null;
    }

    @Override
    public Student create(Student dto) {
        try(PreparedStatement statement =
                    this.connection.prepareStatement(INSERT);){
            Main.prepareStatementForStudentObject(dto, statement);
        }catch (SQLException e){e.printStackTrace();throw new RuntimeException(e);}
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
