package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

//this is the room database class
@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
//    private static final int NUMBER_OF_THREADS = 4;
//    //use this to run database operations asychronously
//    //on a background thread.
//    static final ExecutorService databaseWriteExecutor=
//            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String DATABASE_NAME = "StudentDB";
    public abstract StudentDAO studentDao();

    //create a Singleton Pattern to have one instance of the DB
    public static synchronized AppDatabase getInstance(final Context context){
        if (INSTANCE == null){
            //Create database object
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }
}
