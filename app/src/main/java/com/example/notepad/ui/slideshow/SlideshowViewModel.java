package com.example.notepad.ui.slideshow;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public
class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    TextView textView12 ;

    public
    SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public
    LiveData<String> getText() {
        return mText;
    }
}