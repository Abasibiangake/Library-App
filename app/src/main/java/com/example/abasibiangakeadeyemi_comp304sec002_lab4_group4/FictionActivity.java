package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FictionActivity extends AppCompatActivity {
//    public static final String category="Fiction";
    public static final  int ADD_BOOK_REQUEST=1;
    public static final  int EDIT_BOOK_REQUEST=2;
    private RecyclerView recyclerView;
    private BooksViewModel booksViewModel;
    private SharedPreferences pref,prefLib;
    public Student student;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiction2);
        setTitle("Fiction Books");
        pref= getSharedPreferences("login",0);
        //prefLib= getSharedPreferences("Liblogin", 0);
        FloatingActionButton floatingActnBtn=findViewById(R.id.floating_button);

        int studentId=pref.getInt("studentId",-1);
        int librarianId = pref.getInt("librarianId",-1);

        //used to get the current id depending on which user (librarian or student has been logged in
        int currentid = getIntent().getIntExtra("module", -1);
        Toast.makeText(FictionActivity.this,
                "librarian ID IS "+librarianId + "\nSTUDENT ID is! " + studentId
                        + "\nCurrent ID is! " + currentid, Toast.LENGTH_SHORT).show();

        //compare based on id of logged in user
        if (currentid == librarianId){
            floatingActnBtn.show();
        }
        //hide the floating action button if its a student logged in
        if(studentId == currentid ){
            floatingActnBtn.hide();
//            Toast.makeText(FictionActivity.this,
//                    "STUDENT ID MORE THAN 1! " + studentId, Toast.LENGTH_SHORT).show();
        }


        floatingActnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FictionActivity.this,AddEditbookActivity.class);
                startActivityForResult(intent,ADD_BOOK_REQUEST);
            }
        });

        recyclerView=findViewById(R.id.fiction_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        BooksAdapter adapter= new BooksAdapter();
        recyclerView.setAdapter(adapter);
        booksViewModel=new ViewModelProvider(this).get(BooksViewModel.class);
        booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>() {
            @Override
            public void onChanged(List<Books> books) {
                List<Books>fiction=new ArrayList<>();
                for(Books fictionBooks:books){
                    if(fictionBooks.getCategory().equals("Fiction")){
                        fiction.add(fictionBooks);
                    }
                }
                adapter.setBooks(fiction);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                booksViewModel.delete(adapter.getBookAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new BooksAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Books book) {
                Intent intent=new Intent(FictionActivity.this,AddEditbookActivity.class);
                intent.putExtra(AddEditbookActivity.EXTRA_ID,book.getBookId());
                intent.putExtra(AddEditbookActivity.EXTRA_FICTION_TITLE,book.getBookName());
                intent.putExtra(AddEditbookActivity.EXTRA_FICTION_AUTHOR,book.getAuthorName());
                intent.putExtra(AddEditbookActivity.EXTRA_FICTION_CATEGORY,book.getCategory());
                intent.putExtra(AddEditbookActivity.EXTRA_FICTION_QUANTITY,book.getQuantity());
                intent.putExtra(AddEditbookActivity.EXTRA_FICTION_DESCRIPTION,book.getBookDescription());
                startActivityForResult(intent,EDIT_BOOK_REQUEST);

            }

            @Override
            public void onBorrowBtnClick(Books borrowedBooks) {
                Toast.makeText(FictionActivity.this,
                        "This book Id is: "+borrowedBooks.getBookId(), Toast.LENGTH_SHORT).show();
                SharedPreferences borrowbookPreference = getSharedPreferences("bookBorrowed", 0);
                SharedPreferences.Editor prefsEditor = borrowbookPreference.edit();
                prefsEditor.putString("bookTitle",borrowedBooks.getBookName());
                prefsEditor.putString("bookAuthor",borrowedBooks.getAuthorName());
                prefsEditor.putString("bookCategory",borrowedBooks.getCategory());
                prefsEditor.putString("bookDesc",borrowedBooks.getBookDescription());
                prefsEditor.putInt("bookId",borrowedBooks.getBookId());//
                prefsEditor.commit();


                int bookDeleteWithID = borrowedBooks.getBookId();
                for (Books b: booksViewModel.getAllBooks().getValue()){
                    if (b.getBookId() == bookDeleteWithID){
                        int newQuantity = b.getQuantity() -1;
                        b.setQuantity(newQuantity);
                        booksViewModel.update(b);
                        Toast.makeText(FictionActivity.this,
                                "You have borrowed this book ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_BOOK_REQUEST && resultCode==RESULT_OK){
            String title=data.getStringExtra(AddEditbookActivity.EXTRA_FICTION_TITLE);
            String description=data.getStringExtra(AddEditbookActivity.EXTRA_FICTION_DESCRIPTION);
            String author=data.getStringExtra(AddEditbookActivity.EXTRA_FICTION_AUTHOR);
            int quantity= data.getIntExtra(AddEditbookActivity.EXTRA_FICTION_QUANTITY,1);
            String category=data.getStringExtra(AddEditbookActivity.EXTRA_FICTION_CATEGORY);
            Books savedBook=new Books(title,author,description,category,quantity);
            booksViewModel.insert(savedBook);
            Toast.makeText(this,"Book saved", Toast.LENGTH_SHORT).show();
        }else if(requestCode==EDIT_BOOK_REQUEST && resultCode==RESULT_OK){
            int id=data.getIntExtra(AddEditbookActivity.EXTRA_ID,-1);
            if(id==-1){
                Toast.makeText(this,"Cannot find book", Toast.LENGTH_SHORT).show();
                return;
            }
            String title=data.getStringExtra(AddEditbookActivity.EXTRA_FICTION_TITLE);
            String description=data.getStringExtra(AddEditbookActivity.EXTRA_FICTION_DESCRIPTION);
            String author=data.getStringExtra(AddEditbookActivity.EXTRA_FICTION_AUTHOR);
            String category=data.getStringExtra(AddEditbookActivity.EXTRA_FICTION_CATEGORY);
            int quantity= data.getIntExtra(AddEditbookActivity.EXTRA_FICTION_QUANTITY,1);
            Books editedBook=new Books(title,author,description,category,quantity);
            editedBook.setBookId(id);
            booksViewModel.update(editedBook);
            Toast.makeText(this,"Book updated", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Book not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.profile);
        int studentId=pref.getInt("studentId",-1);
        int librarianId = pref.getInt("librarianId",-1);
        //hidng the profile icon if it's not a student logged in
        if(item!=null && studentId<1){
            item.setVisible(false);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        pref= getSharedPreferences("login",0);
        int Id=pref.getInt("studentId",-1);
        switch (item.getItemId()){
            case R.id.fiction:
                Intent fictionIntent = new Intent(this, FictionActivity.class);
                fictionIntent.putExtra("module", Id);
                startActivity(fictionIntent);
                break;
            case R.id.non_fiction:
                Intent nonfictionIntent = new Intent(this, NonFictionActivity.class);
                startActivity(nonfictionIntent);
                nonfictionIntent.putExtra("module", Id);
                break;
            case R.id.educational:
                Intent educationalIntent = new Intent(this, EducationalActivity.class);
                startActivity(educationalIntent);
                break;
            case R.id.history:
                Intent historyIntent = new Intent(this, HistoryActivity.class);
                startActivity(historyIntent);
                break;
            case R.id.profile:
                Intent profileIntent = new Intent(this, StudentProfileActivity.class);
                startActivity(profileIntent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}

