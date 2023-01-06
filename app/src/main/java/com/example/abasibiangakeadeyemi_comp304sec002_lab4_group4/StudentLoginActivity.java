package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class StudentLoginActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    Student student;
    //sign up page
    private Button btnStudentLogin;
    private EditText editStudentLoginPassword, editStudentLoginId;
    String outputStudentID = " ";
    String outputStudentPassword = " ";
    String outputStudentFirstName = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        //imitialise variables
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        editStudentLoginPassword =  findViewById(R.id.studentLoginPassword);
        editStudentLoginId = findViewById(R.id.studentLoginID);

        btnStudentLogin = findViewById(R.id.btnStudentLogin);
        btnStudentLogin.setOnClickListener(new View.OnClickListener() {
            //Implement the event handler method
            public void onClick(View v) {
                String userLoginID = editStudentLoginId.getText().toString();

                String studentLoginPassword = editStudentLoginPassword.getText().toString();

                if (userLoginID.isEmpty() || studentLoginPassword.isEmpty()) {
                    Toast.makeText(StudentLoginActivity.this, "Please enter the valid login details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    int StudentLoginID = Integer.parseInt(userLoginID);
                    studentViewModel.getStudent(StudentLoginID ).observe(StudentLoginActivity.this, new Observer<Student >() {

                        @Override
                        public void onChanged(Student student) {
                            if(student!=null && student.getPassword().equals(studentLoginPassword)) {
                                Toast.makeText(StudentLoginActivity.this,
                                        "You are succesfully logged in! ", Toast.LENGTH_SHORT).show();
                                SharedPreferences loginPreference = getSharedPreferences("login", 0);
                                SharedPreferences.Editor prefsEditor = loginPreference.edit();
                                 prefsEditor.putString("studentFName",student.getFirstname());
                                prefsEditor.putString("studentLName",student.getLastname());
                                prefsEditor.putInt("studentId",student.getStudentId());
//
                                prefsEditor.commit();
                                Intent intent = new Intent(StudentLoginActivity.this, FictionActivity.class);
                                intent.putExtra("module", student.getStudentId());
                                startActivity(intent);
                            }else{
                                Toast.makeText(StudentLoginActivity.this,
                                        "You entered invalid login details.\nTry Again! ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }
            }
        });



        //if the LiveData already has data it will be delivered
        // to the observer
//        studentViewModel.getStudent().observe(this, new Observer<Student >() {
//            @Override
//            public void onChanged(@Nullable Student result) {
//
////                for (Student student : result) {
////                    outputStudentID += student.getStudentId() + "\n";
////                    outputStudentPassword += student.getPassword() + "\n";
////                    outputStudentFirstName += student.getFirstname() + "\n";
////                }
////                Log.i("",outputStudentID);
////                Log.i("",outputStudentFirstName);
////                Toast.makeText(StudentLoginActivity.this,
////                        outputStudentID +", "+outputStudentPassword, Toast.LENGTH_SHORT).show();
//
//                btnStudentLogin = findViewById(R.id.btnStudentLogin);
//                btnStudentLogin.setOnClickListener(new View.OnClickListener() {
//                    //Implement the event handler method
//                    public void onClick(View v) {
//                        String userLoginID = editStudentLoginId.getText().toString();
//
//                        String studentLoginPassword = editStudentLoginPassword.getText().toString();
//
//                        if (userLoginID.isEmpty() || studentLoginPassword.isEmpty()) {
//                            Toast.makeText(StudentLoginActivity.this, "Please enter the valid login details.", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        else{
//                            int StudentLoginID = Integer.parseInt(userLoginID);
//                            if (outputStudentID.contains(userLoginID) && outputStudentPassword.contains(studentLoginPassword)){
//                                Toast.makeText(StudentLoginActivity.this,
//                                        "You are succesfully logged in! ", Toast.LENGTH_SHORT).show();
//                                SharedPreferences loginPreference= getSharedPreferences("login", 0);
//                                SharedPreferences.Editor prefsEditor= loginPreference.edit();
////                                String studentName= ;
////                                prefsEditor.putString("payment",totalAmount);
////                                prefsEditor.commit();
//                                Intent intent=new Intent(StudentLoginActivity.this,FictionActivity.class);
//                                startActivity(intent);
//                            }
//                            else{
//                                Toast.makeText(StudentLoginActivity.this,
//                                        "You entered invalid login details.\nTry Again! ", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//            }
//        });
    }
}

