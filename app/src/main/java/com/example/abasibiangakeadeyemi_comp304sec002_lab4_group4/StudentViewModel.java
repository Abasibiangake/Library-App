package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

//provides data to the UI and acts as a communication center
// between the Repository and the UI.
public class StudentViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private StudentRepository studentRepository;
    private LiveData<List<Student>> allStudent;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        allStudent = studentRepository.getAllStudents();
    }

    //calls repository to insert a new student
    public void insertStudent(Student student){
        studentRepository.insertStudent(student);
    }

    public void deleteStudent(Student student){
        studentRepository.deleteStudent(student);
    }

    public void updateStudent(Student student){
        studentRepository.updateStudent(student);
    }

    //returns query results as live data object
    LiveData<List<Student>> getAllStudents() { return allStudent; }

}
