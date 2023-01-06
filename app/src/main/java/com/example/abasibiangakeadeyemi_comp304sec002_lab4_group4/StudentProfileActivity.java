package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentProfileActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "abasiadeyemi.dashboard.borrowed.book.EXTRA_ID";
    public static final String EXTRA_FICTION_TITLE = "abasiadeyemi.dashboard.borrowed.book.EXTRA_TITLE";
    public static final String EXTRA_FICTION_DESCRIPTION = "abasiadeyemi.dashboard.borrowed.book.EXTRA_DESCRIPTION";
    public static final String EXTRA_FICTION_CATEGORY = "abasiadeyemi.dashboard.borrowed.book.EXTRA_CATEGORY";
    public static final String EXTRA_FICTION_AUTHOR = "abasiadeyemi.dashboard.borrowed.book.EXTRA_AUTHOR";
    public static final String EXTRA_FICTION_QUANTITY = "abasiadeyemi.dashboard.borrowed.book.EXTRA_QUANTITY";
    private EditText editTextTitle, editTextCategory, editTextAuthor, editTextDescription, editTextQuantity;
    private SharedPreferences pref, prefborrow;
    private TextView studentFName,list_of_borrowed_books ;
    private TextView studentLName;
    private TextView studentId;
    private RecyclerView recyclerView;
    private List<Books> borrowedBooksArray = new ArrayList<>();
    private BooksViewModel booksViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        list_of_borrowed_books = findViewById(R.id.list_of_borrowed_books);
        pref = getSharedPreferences("login", 0);

        if (pref.getInt("studentId", 1) > -1) {
            studentFName = findViewById(R.id.profile_studentFName);
            studentLName = findViewById(R.id.profile_studentLName);
            studentId = findViewById(R.id.profile_studentId);
            studentLName.setText("Student Last Name: " + pref.getString("studentLName", ""));
            studentId.setText("Student ID: " + String.valueOf(pref.getInt("studentId", 1)));
            studentFName.setText("Student First Name: " + pref.getString("studentFName", ""));
        }

        prefborrow = getSharedPreferences("bookBorrowed", 0);
        if (prefborrow.getInt("bookId", 1) > -1){
            int bookId = prefborrow.getInt("bookId", 1);
            recyclerView=findViewById(R.id.borrowed_book_recycler);
            //TextView bookName = recyclerView.findViewById(R.id.tv_book_name);


            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            BooksAdapter adapter= new BooksAdapter();
            recyclerView.setAdapter(adapter);
            booksViewModel=new ViewModelProvider(this).get(BooksViewModel.class);
            booksViewModel.getBooks(bookId).observe(this, new Observer<Books>() {
                @Override
                public void onChanged(Books books) {
                    String borrowedBookList = "";
                   borrowedBookList += "A "+ books.getCategory() +" book: " + books.getBookName() + " by " + books.getAuthorName();

                    list_of_borrowed_books.setText(borrowedBookList);

                    // -----------To Display Borrow Books as a list, use code below---------
//                    List<Books>borrowedBooksArray=new ArrayList<>();
//                    borrowedBooksArray.add(books);
//                    adapter.setBooks(borrowedBooksArray);
//                    Toast.makeText(StudentProfileActivity.this,
//                            "Current book id is "+ bookId, Toast.LENGTH_SHORT).show();
                }


            });

        }
    }
}