package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {
    private BooksRepository booksRepository;

    private LiveData<List<Books>> allBooks;
    public BooksViewModel(@NonNull Application application) {
        super(application);
        booksRepository=new BooksRepository(application);
         allBooks=booksRepository.getAllBooks();
    }

    public void insert(Books book){
        booksRepository.insertBook(book);
    }

    public void update(Books book){
        booksRepository.updateBook(book);
    }
    public void delete(Books book){
        booksRepository.delete(book);
    }
    //returns query results as live data object
   public LiveData<List<Books>> getAllBooks(){ return allBooks; };
}
