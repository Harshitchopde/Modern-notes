package com.example.notepad.ui.slideshow;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.Adapters.PDFRecycleAdapter;

import com.example.notepad.OnPdfItemSelectListnear;
import com.example.notepad.PDFViewerActivity;
import com.example.notepad.databinding.FragmentSlideshowBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public
class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    private
    RecyclerView recyclerViewPDF;
    PDFRecycleAdapter adapter;
    static List<File> pdffile;
    boolean isgranted = false;

public
    View onCreateView(@NonNull LayoutInflater inflater,
                      ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerViewPDF = binding.pdfRecycleView;
        pdffile = new ArrayList<>();

        runtimepremission();
        showPDF();







//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


 public
    ArrayList<File>  findPDF(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for(File single:files){
            if (single.isDirectory() && !single.isHidden()){
                arrayList.addAll(findPDF(single));
            }
            else {
//                if (single.getName().endsWith(".pdf")){
//                    arrayList.add(single);
//                }


                    arrayList.add(single);

            }
        }
        return arrayList;
 }
    private
    void runtimepremission() {
        Dexter.withContext(getContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(
                new PermissionListener() {
                    @Override
                    public
                    void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            isgranted = true;
                    }

                    @Override
                    public
                    void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(getContext(), "premission Required", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public
                    void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }
        ).check();
    }

    @Override
    public
    void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void showPDF(){
        if (pdffile!=null ) {
            pdffile.addAll(findPDF(Environment.getExternalStorageDirectory()));
            Log.e(TAG, "showPDF: count " );

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public
            void run() {
                adapter = new PDFRecycleAdapter(getContext(), pdffile, new OnPdfItemSelectListnear() {
                    @Override
                    public
                    void onPdfClick(File file) {
                        startActivity(new Intent(getContext(), PDFViewerActivity.class).putExtra("path",file.getPath()));
                    }
                });
//                adapter = new PDFRecycleAdapter(getContext(), pdffile, (OnPdfItemSelectListnear) getActivity());
                recyclerViewPDF.setHasFixedSize(true);
                recyclerViewPDF.setLayoutManager(new GridLayoutManager(getContext(), 1));
                recyclerViewPDF.setAdapter(adapter);
            }
        },800);
    }


}