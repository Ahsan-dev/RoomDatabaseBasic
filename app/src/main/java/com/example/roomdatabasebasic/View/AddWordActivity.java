package com.example.roomdatabasebasic.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabasebasic.R;

public class AddWordActivity extends AppCompatActivity {

    private EditText wordEdt;
    private Button addBtn;
    public static final String REPLY_TAG = "REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        wordEdt = findViewById(R.id.new_word_edt_id);
        addBtn = findViewById(R.id.new_word_add_btn_id);

        addBtn.setOnClickListener(v -> {

            Intent replyIntent = new Intent();

            if(TextUtils.isEmpty(wordEdt.getText().toString())){

                setResult(RESULT_CANCELED,replyIntent);

            }else{

                String word = wordEdt.getText().toString();
                replyIntent.putExtra(REPLY_TAG,word);
                setResult(RESULT_OK,replyIntent);

            }
            finish();

        });

    }
}