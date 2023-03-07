package com.example.notepad;


import static com.example.notepad.R.color.white;

import android.annotation.SuppressLint;
import android.view.animation.OvershootInterpolator;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public
class Fab_control {
    ConstraintLayout fablayout;

    FloatingActionButton FabMain,Fab1,Fab2,Fab3;
    Float translationY = 100f;
    OvershootInterpolator interpolator = new OvershootInterpolator();
   public
   Fab_control(FloatingActionButton fabmain, FloatingActionButton fab1, FloatingActionButton fab2, FloatingActionButton fab3, ConstraintLayout fablayout){
       this.Fab1 = fab1;
       this.Fab2= fab2;
       this.Fab3 = fab3;
       this.FabMain = fabmain;
       this.fablayout=fablayout;

   }



    @SuppressLint("ResourceAsColor")
    public void openMenu(){
//       fablayout.setBackgroundColor(R.color.black);
        FabMain.animate().setInterpolator(interpolator).rotation(3f).setDuration(300).start();
        Fab1.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        Fab2.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        Fab3.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();


    }

    public void closeMenu(){

        FabMain.animate().setInterpolator(interpolator).rotation(60f).setDuration(300).start();
        Fab1.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        Fab2.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        Fab3.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();



    }

}
