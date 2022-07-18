package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LibrarianLoginActivity extends AppCompatActivity {

    private LibrarianViewModel librarianViewModel;
    Librarian librarian;
    //sign up page
    private Button btnLibrarianLogin;
    private EditText editLibrarianLoginPassword, editLibrarianLoginId;
    String outputLibrarianID = " ";
    String outputLibrarianPassword = " ";
    String outputLibrarianFirstName = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);
        //imitialise variables
        librarianViewModel = ViewModelProviders.of(this).get(LibrarianViewModel.class);

        editLibrarianLoginPassword =  findViewById(R.id.librarianPassword);
        editLibrarianLoginId = findViewById(R.id.librarianID);

        //if the LiveData already has data it will delivered
        // to the observer
        librarianViewModel.getAllLibrarian().observe(this, new Observer<List<Librarian>>() {
            @Override
            public void onChanged(@Nullable List<Librarian> resultLibrarian) {

                for (Librarian librarian : resultLibrarian) {
                    outputLibrarianID += librarian.getLibrarianId() + "\n";
                    outputLibrarianPassword += librarian.getPassword() + "\n";
                    outputLibrarianFirstName += librarian.getFirstName() + "\n";
                }
//                Toast.makeText(LibrarianLoginActivity.this,
//                        outputLibrarianID +", "+outputLibrarianPassword, Toast.LENGTH_SHORT).show();

                btnLibrarianLogin = findViewById(R.id.btnLibrarianLogin);
                btnLibrarianLogin.setOnClickListener(new View.OnClickListener() {
                    //Implement the event handler method
                    public void onClick(View v) {
                        String userLibLoginID = editLibrarianLoginId.getText().toString();

                        String librarianLoginPassword = editLibrarianLoginPassword.getText().toString();

                        if (userLibLoginID.isEmpty() || librarianLoginPassword.isEmpty()) {
                            Toast.makeText(LibrarianLoginActivity.this, "Please enter the valid login details.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            int librarianLoginID = Integer.parseInt(userLibLoginID);
                            if (outputLibrarianID.contains(userLibLoginID) && outputLibrarianPassword.contains(librarianLoginPassword)){
                                Toast.makeText(LibrarianLoginActivity.this,
                                        "You are succesfully logged in as a Librarian! ", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LibrarianLoginActivity.this,
                                        "You entered invalid login details.\nTry Again! ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}
