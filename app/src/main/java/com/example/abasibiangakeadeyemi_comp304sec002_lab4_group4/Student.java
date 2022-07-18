package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey
    private int studentId;
    private String firstname;
    private String lastname;
    private String password;
    private int bookId;

//    public Student(){}
    public Student(int studentId, String firstname, String lastname, String password) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

}
