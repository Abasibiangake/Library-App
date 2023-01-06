package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import android.app.Application;
import android.os.AsyncTask;
import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;

public class StudentRepository {
    private final StudentDAO studentDAO;
    private LiveData<List<Student>> studentList;
    private LiveData<Student> student;

    public StudentRepository(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        studentDAO= db.studentDao();
        //call interface method
        studentList = studentDAO.getAllStudents();
    }
    //return query result as LiveData object
    LiveData<List<Student>> getAllStudents(){
        return studentList;
    }

    //inserts a student asynchronously
    public void insertStudent(Student student) {
        insertStudentAsync(student);
    }

    //gets student by id
    public LiveData<Student> getStudentById(int id){ return studentDAO.getStudentById(id); }
    //updates a student asynchronously
    public void updateStudent(Student student) {
        updateStudentAsync(student);
    }

    //deletes a person asynchronously
    public void deleteStudent(Student student) { deleteStudentAsync(student); }

    //Insert and other queries must be done in AsyncTask or IT SHOULD NOT BE DONE ON MAIN UI THREAD
    private void insertStudentAsync(final Student student) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentDAO.insertStudent(student);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    private void getStudentByIdAsync(final int id) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    studentDAO.getStudentById(id);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    //Update and other queries must be done in AsyncTask or IT SHOULD NOT BE DONE ON MAIN UI THREAD
    private void updateStudentAsync(final Student student) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentDAO.updateStudent(student);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void deleteStudentAsync(final Student student) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    studentDAO.deleteStudent(student);
                } catch (Exception e) {
                    e.printStackTrace(); //added
                }
            }
        }).start();
    }

}
