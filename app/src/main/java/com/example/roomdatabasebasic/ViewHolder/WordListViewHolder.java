package com.example.roomdatabasebasic.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.roomdatabasebasic.R;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WordListViewHolder extends RecyclerView.ViewHolder {

    public TextView wordNameTxt;

    public WordListViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        wordNameTxt = itemView.findViewById(R.id.list_item_text_id);


    }
}
