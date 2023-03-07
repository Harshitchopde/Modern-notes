package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public
class PDFViewerActivity extends AppCompatActivity {
    PDFView pdfView;
    String filepath;
    Toolbar toolbar;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        pdfView = findViewById(R.id.pdfViewer);


        filepath = getIntent().getStringExtra("path");
        File file = new File(filepath);
        Uri path = Uri.fromFile(file);
        pdfView.fromUri(path).load();



    }
}