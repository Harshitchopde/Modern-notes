package com.example.notepad;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.notepad.Database.Notes;

public
class NotesViewActivity extends AppCompatActivity {
    TextView wordOP,meaningOp;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);
        wordOP = findViewById(R.id.word_op);
        meaningOp = findViewById(R.id.meaning_op);



        Intent intent = getIntent();
        Notes word = (Notes) intent.getSerializableExtra("word");
        Log.e(TAG, "onCreate: "+word.getWord()+" "+word.getMeaning() );
        wordOP.setText(word.getWord());
        meaningOp.setText(word.getMeaning());
    }
}