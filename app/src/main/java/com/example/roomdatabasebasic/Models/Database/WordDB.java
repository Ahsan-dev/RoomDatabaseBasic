package com.example.roomdatabasebasic.Models.Database;


import android.content.Context;

import com.example.roomdatabasebasic.Models.Dao.WordDao;
import com.example.roomdatabasebasic.Models.Entity.Word;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDB extends RoomDatabase {

    public abstract WordDao WordDao();

    private static volatile WordDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WordDB getDatbase(Context context){

        if(INSTANCE==null){

            synchronized (WordDB.class){

                if(INSTANCE==null){

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDB.class,
                            "word_database").build();

                }


            }

        }
        return INSTANCE;

    }


    private static RoomDatabase.Callback wordDbCallback = new RoomDatabase.Callback(){


        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{


                WordDao dao = INSTANCE.WordDao();

                dao.deleteAll();

            });

        }
    };

}
