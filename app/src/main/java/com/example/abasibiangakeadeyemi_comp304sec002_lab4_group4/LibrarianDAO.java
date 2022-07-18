package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LibrarianDAO {
    // Insert one new librarian to the database.
    @Insert
    void insert(Librarian librarian);

    //monitor query result changes with live data
    @Query("select * from Librarian order by firstname")
    LiveData<List<Librarian>> getAllLibrarian();
}
