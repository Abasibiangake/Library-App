package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

//provides data to the UI and acts as a communication center
// between the LibraryRepository and the UI.
public class LibrarianViewModel extends AndroidViewModel {
    // calling repository tasks and
    // sending the results to the Activity
    private LibrarianRepository librarianRepository;
    private LiveData<List<Librarian>> allLibrarians;

    public LibrarianViewModel(@NonNull Application application) {
        super(application);
        librarianRepository = new LibrarianRepository(application);
        allLibrarians = librarianRepository.getAllLibrarian();
    }

    //calls repository to insert a new librarian
    public void insertStudent(Librarian librarian){
        librarianRepository.insert(librarian);
    }

    //returns query results as live data object
    LiveData<List<Librarian>> getAllLibrarian() { return allLibrarians; }

}
