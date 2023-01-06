package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import android.app.Application;
import android.os.AsyncTask;
import android.content.Context;
import androidx.lifecycle.LiveData;
import java.util.List;

public class LibrarianRepository {
    private final LibrarianDAO librarianDAO;
    private LiveData<List<Librarian>> librarianList;

    public LibrarianRepository(Context context) {
        //create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //create an interface object
        librarianDAO= db.librarianDao();
        //call interface method
        librarianList = librarianDAO.getAllLibrarian();
    }
    //return query result as LiveData object
    LiveData<List<Librarian>> getAllLibrarian(){
        return librarianList;
    }

    //gets librarian by id
    public LiveData<Librarian> getLibrarianById(int id){ return librarianDAO.getLibrarianById(id); }

    //inserts a student asynchronously
    public void insert(Librarian librarian) {
        insertLibrarianAsync(librarian);
    }

    //Insert and other queries must be done in AsyncTask or IT SHOULD NOT BE DONE ON MAIN UI THREAD
    private void insertLibrarianAsync(final Librarian librarian) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    librarianDAO.insert(librarian);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
