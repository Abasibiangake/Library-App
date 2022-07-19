package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BooksDao {
    @Insert
    void insert(Books book);

    @Query("select * from booksTable order by bookName")
    LiveData<List<Books>> getAllBooks();

    @Update
    void update(Books book);

    @Delete
    void delete(Books book);
}


