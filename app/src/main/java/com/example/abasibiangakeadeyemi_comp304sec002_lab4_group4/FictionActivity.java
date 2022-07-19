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
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiction2);
        setTitle("Fiction Books");
        pref= getSharedPreferences("login",0);

        FloatingActionButton floatingActnBtn=findViewById(R.id.floating_button);
        int studentId=pref.getInt("studentId",-1);
        //hide the floating action button if its a student logged in
        if(studentId!=-1){
floatingActnBtn.hide();
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
        //hidng the profile icon if it's not a student logged in
        if(item!=null && studentId<1){
            item.setVisible(false);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fiction:
                Intent fictionIntent = new Intent(this, FictionActivity.class);
                startActivity(fictionIntent);
                break;
            case R.id.non_fiction:
                Intent nonfictionIntent = new Intent(this, NonFictionActivity.class);
                startActivity(nonfictionIntent);
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

