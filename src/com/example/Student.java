package com.example;

import com.example.Util.DataTransferObject;

public class Student implements DataTransferObject {
    private int id;
    private String firstName;
    private String lastName;
    private String subject;
    private String marks;

    public Student(){}
    public Student(int id, String firstName, String lastName, String subject, String marks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subject='" + subject + '\'' +
                ", marks='" + marks + '\'' +
                '}';
    }


}
