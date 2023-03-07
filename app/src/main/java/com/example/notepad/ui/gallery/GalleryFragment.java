package com.example.notepad.ui.gallery;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.notepad.databinding.FragmentGalleryBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public
class GalleryFragment extends Fragment {

    private static final String TAG = "galleryFragment";
    private FragmentGalleryBinding binding;
    Button createFile;
private File file;
    public
    View onCreateView(@NonNull LayoutInflater inflater,
                      ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        createFile=binding.buttonCreateFile;
        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        premissionToWrite();
        premissionToRead();
//        File musicDirectory = Context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
//        File file = new File(getContext().getFilesDir(), "IMG_2022014.jpg");
//        if (file.exists()) {
//            // Do something with the file
//            Log.e(TAG, "onCreateView: "+file.getPath());
//        }
//        else{
//
//        }
//        onCreateFileBtnClick();
//        file= new File(Environment.getExternalStorageDirectory(), "MyFile.txt");
//        Log.e("d", "onCreateView: "+Environment.getExternalStorageDirectory() );
////        Log.e("d", "onCreateView: "+ Context.getExternalFilesDir() );
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//            fos.write("Hello World!".getBytes());
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return root;
    }

    private
    void onCreateFileBtnClick() {
        createFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View view) {


                FileInputStream fis;
                try {
                    fis = new FileInputStream(file);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fis.close();
                    String text = new String(buffer);
                    Log.e("df", "onClick: "+text );
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private
    void premissionToRead() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
        }
    }

    private
    void premissionToWrite() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public
    void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}