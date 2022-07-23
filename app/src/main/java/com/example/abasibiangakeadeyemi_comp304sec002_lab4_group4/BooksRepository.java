package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import android.app.Application;
//import android.arch.lifecycle.LiveData;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BooksRepository {
    private BooksDao booksDao;
    private LiveData<List<Books>> booksList;

    public BooksRepository(Application application){
        AppDatabase db= AppDatabase.getInstance(application);

        booksDao=db.booksDao();
        booksList=booksDao.getAllBooks();
    }
   public LiveData<List<Books>> getAllBooks(){return booksList;}
    public void insertBook(Books book){
        insertAsync(book);
    }
    public void updateBook(Books book){
        updateAsync(book);
    }

    public void deleteBook(Books book){

    }
    //deletes a person asynchronously
    public void delete(Books book) { deleteAsync(book); }

    private void insertAsync(final Books book){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    booksDao.insert(book);
                }catch(Exception e){

                }
            }
        }).start();
    }
    private void updateAsync(final Books book){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    booksDao.update(book);
                }catch(Exception e){

                }
            }
        }).start();
    }

    private void deleteAsync(final Books book) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    booksDao.delete(book);
                    //deleteResult.postValue(1);
                } catch (Exception e) {
                    //deleteResult.postValue(0);
                }
            }
        }).start();
    }

    public LiveData<Books> getBookById(int id) {

        //gets book by id
         return booksDao.getBookById(id);
    }
}


