package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import androidx.lifecycle.LiveData;

import java.util.List;

// this interface declares database functions
// and does the mapping of SQL queries to functions
@Dao
public interface StudentDAO
{
    // Insert one new student to the database.
    @Insert
    public void insertStudent(Student student);

    // Update a student details in the database
    // based on the student id entered (primary key).
    @Update
    public void updateStudent(Student student);

    @Delete
    public void deleteStudent(Student student);

    //monitor query result changes with live data
    @Query("select * from Student order by firstname")
    LiveData<List<Student>>getAllStudents();
    //get student by student id
    @Query("select * from Student where studentId= :id")
    LiveData<Student>getStudentById(int id);


}
