package com.example.roomdatabasebasic.ViewModel;

import android.app.Application;

import com.example.roomdatabasebasic.Models.Entity.Word;
import com.example.roomdatabasebasic.Models.Repository.WordRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private LiveData<List<Word>> listLiveData;

    public WordViewModel(@NonNull @NotNull Application application) {
        super(application);

        wordRepository = new WordRepository(application);
        listLiveData = wordRepository.getAllWords();

    }

    public LiveData<List<Word>>  getWords(){

        return listLiveData;

    }

    public void insertWord(Word word){

        wordRepository.insert(word);
    }
}
