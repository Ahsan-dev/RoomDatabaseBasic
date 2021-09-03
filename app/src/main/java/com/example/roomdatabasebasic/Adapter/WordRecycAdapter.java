package com.example.roomdatabasebasic.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roomdatabasebasic.Models.Entity.Word;
import com.example.roomdatabasebasic.R;
import com.example.roomdatabasebasic.ViewHolder.WordListViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordRecycAdapter extends RecyclerView.Adapter<WordListViewHolder> {

    private List<Word> wordList;

    public WordRecycAdapter(List<Word> wordList) {
        this.wordList = wordList;
    }


    @NotNull
    @Override
    public WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout,parent,false);
        return new WordListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WordListViewHolder holder, int position) {

        Word word = wordList.get(position);

        holder.wordNameTxt.setText(word.getWord());

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
