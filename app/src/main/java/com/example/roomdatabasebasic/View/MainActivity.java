package com.example.roomdatabasebasic.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.roomdatabasebasic.Adapter.WordRecycAdapter;
import com.example.roomdatabasebasic.Models.Entity.Word;
import com.example.roomdatabasebasic.R;
import com.example.roomdatabasebasic.ViewModel.WordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Word> wordList;
    private WordRecycAdapter wordRecycAdapter;
    private WordViewModel viewModel;
    private FloatingActionButton addBtn;
    private static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(WordViewModel.class);

        recyclerView = findViewById(R.id.recyclerview_id);
        addBtn = findViewById(R.id.add_button);

        wordList = new ArrayList<>();





        viewModel.getWords().observe(this, words -> {

            wordList = words;
            Toast.makeText(this, String.valueOf(wordList.size()), Toast.LENGTH_SHORT).show();
            wordRecycAdapter = new WordRecycAdapter(wordList);
            recyclerView.hasFixedSize();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(wordRecycAdapter);
            wordRecycAdapter.notifyDataSetChanged();

        });





        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(

                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if(result.getResultCode() == Activity.RESULT_OK){

                        Word word = new Word(result.getData().getStringExtra(AddWordActivity.REPLY_TAG));
                        viewModel.insertWord(word);

                    }else
                        Toast.makeText(this, "Not added", Toast.LENGTH_SHORT).show();

                }

        );

        addBtn.setOnClickListener(v -> {

            Intent intent = new Intent(this,AddWordActivity.class);
            someActivityResultLauncher.launch(intent);

        });

    }

    
}