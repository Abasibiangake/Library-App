package com.example.abasibiangakeadeyemi_comp304sec002_lab4_group4;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.os.AsyncTask;

//this is the room database class
@Database(entities = {Librarian.class, Student.class,Books.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    private static final String DATABASE_NAME = "LibraryDB";
    public abstract StudentDAO studentDao();
    public abstract LibrarianDAO librarianDao();
    public abstract BooksDao booksDao();


    //singleton pattern to have one instance of db
    public static synchronized AppDatabase getInstance(Context context){
        if (INSTANCE==null){
            //Create database object
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME)
                    // below line is use to add fall back to
                    // destructive migration to our database.
                    .fallbackToDestructiveMigration()
                    // below line is to add callback
                    // to our database.
                    .addCallback(roomCallback)
                    // below line is to
                    // build our database.
                    .build();
        }
        return  INSTANCE;
    }
    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private LibrarianDAO librarianDao;
        public StudentDAO studentDao;
        //private BooksDao booksDao;

        private PopulateDbAsyncTask(AppDatabase db){
            librarianDao=db.librarianDao();
            studentDao= db.studentDao();
            //booksDao=db.booksDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            librarianDao.insert(new Librarian(1,"Yash","Sheth","COMP304"));
            librarianDao.insert(new Librarian(7,"James","Bond","CasinoRoyale"));
            studentDao.insertStudent(new Student(301208739, "Abasibiangake", "James", "christmas"));
            studentDao.insertStudent(new Student(301158322, "Adeyemi", "Adepoju", "lagosboy"));
            return null;
        }
    }

}
