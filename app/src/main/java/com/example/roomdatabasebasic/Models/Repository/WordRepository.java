package com.example.roomdatabasebasic.Models.Repository;

import android.app.Application;

import com.example.roomdatabasebasic.Models.Dao.WordDao;
import com.example.roomdatabasebasic.Models.Database.WordDB;
import com.example.roomdatabasebasic.Models.Entity.Word;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> getAllWords;

    public WordRepository(Application application) {

        WordDB db = WordDB.getDatbase(application);

        wordDao = db.WordDao();
        getAllWords = wordDao.getAlphabatizedWords();

    }

    public LiveData<List<Word>> getAllWords(){

        return getAllWords;

    }

    public void insert(Word word){

        WordDB.databaseWriteExecutor.execute(()->{

            wordDao.insertWord(word);

        });
    }
}
