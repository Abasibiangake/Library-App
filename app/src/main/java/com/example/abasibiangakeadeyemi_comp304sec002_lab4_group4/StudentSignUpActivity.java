package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class StudentSignUpActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;
    Student student;

    //sign up page
    private Button btnRegister;
    private EditText editStudentSignUpFirstName,editStudentSignUpPassword, editStudentSignUpLastName,
        editStudentSignUpId;

    // creating a constant string variable for our
    // student firstname, lastname, id and password.
    public static final String EXTRA_STUDENT_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_STUDENT_ID";
    public static final String EXTRA_STUDENT_FIRST_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_STUDENT_FIRST_NAME";
    public static final String EXTRA_STUDENT_LAST_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_STUDENT_LAST_NAME";
    public static final String EXTRA_STUDENT_PASSWORD= "com.gtappdevelopers.gfgroomdatabase.EXTRA_STUDENT_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        //imitialise variables
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        editStudentSignUpFirstName = findViewById(R.id.studentFirstName);
        editStudentSignUpPassword =  findViewById(R.id.studentPassword);
        editStudentSignUpLastName = findViewById(R.id.studentLastName);
        editStudentSignUpId = findViewById(R.id.studentID);

        //if the LiveData already has data it will delivered
        // to the observer
        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                String outputStudentFirstName = editStudentSignUpFirstName.getText().toString();
                String outputStudentLastName = editStudentSignUpLastName.getText().toString();
                String outputStudentID = editStudentSignUpId.getText().toString();
                String outputStudentPassword = editStudentSignUpPassword.getText().toString();
                for (Student student : result) {
                    outputStudentFirstName += student.getFirstname() + "\n";
                    outputStudentLastName += student.getLastname() + "\n";
                    outputStudentID += student.getStudentId() + "\n";
                    outputStudentPassword += student.getPassword() + "\n";
                }
//                Toast.makeText(StudentSignUpActivity.this,
//                        outputStudentFirstName
//                            +", "+outputStudentLastName
//                            +", "+outputStudentID
//                            +", "+outputStudentPassword, Toast.LENGTH_SHORT).show();

            }
        });

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            //Implement the event handler method
            public void onClick(View v) {
                String userID = editStudentSignUpId.getText().toString();

                String studentFirstName = editStudentSignUpFirstName.getText().toString();
                String studentLastName = editStudentSignUpLastName.getText().toString();
                String studentPassword = editStudentSignUpPassword.getText().toString();

                if (userID.isEmpty() || studentFirstName.isEmpty() || studentLastName.isEmpty() || studentPassword.isEmpty()) {
                    Toast.makeText(StudentSignUpActivity.this, "Please enter the valid student details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    int StudentID = Integer.parseInt(userID);
                    // displaying a toast message after adding the data
                    student = new Student(StudentID,studentFirstName, studentLastName, studentPassword );

                    //movie.setName(editTextName.getText().toString());
                    studentViewModel.insertStudent(student); // new code
                    Toast.makeText(StudentSignUpActivity.this, "Welcome "+ student.getFirstname()+"!! \nYour details has been saved to Room Database.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void loadMainActivity(View view) {
        Intent intent = new Intent(StudentSignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }
}