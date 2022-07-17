package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadLibrarianLoginScreen(View view) {
        intent = new Intent(MainActivity.this, LibrarianLoginActivity.class);
        startActivity(intent);
    }

    public void loadStudentoginScreen(View view) {
        intent = new Intent(MainActivity.this, StudentLoginActivity.class);
        startActivity(intent);
    }

    public void loadStudentSignUpScreen(View view) {
        intent = new Intent(MainActivity.this, StudentSignUpActivity.class);
        startActivity(intent);
    }
}



// FragmentManager fragmentManager = getFragmentManager();
//FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        LibrarianLoginFragment librarianLoginFragment = new LibrarianLoginFragment();
//        fragmentTransaction.replace(R.id.loadLoginFrame, librarianLoginFragment);
//fragmentTransaction.commit();