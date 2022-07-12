package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
