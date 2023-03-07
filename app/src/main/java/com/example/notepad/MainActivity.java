package com.example.notepad;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.example.notepad.Database.DataBaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notepad.databinding.ActivityMainBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public
class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    FloatingActionButton FabMain, Fab1, Fab2, Fab3;
    ConstraintLayout fablaout;
    DataBaseHelper dbhelper;
    Float translationY = 100f;
    Fab_control fab_control;

    public static Boolean isMenuOpen = false;


    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        initFabMain();
        fab_control = new Fab_control(FabMain, Fab1, Fab2, Fab3,fablaout);
        dbhelper = dbhelper.getDB(getApplicationContext());
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_gallery, R.id.nav_home, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Start writing code hear


    }


    @Override
    public
    boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public
    boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    void initFabMain() {

        FabMain = findViewById(R.id.fabMain);
        Fab1 = findViewById(R.id.fabone);
        Fab2 = findViewById(R.id.fabtwo);
        Fab3 = findViewById(R.id.fabthree);
         fablaout=findViewById(R.id.fablayout);
        Fab1.setAlpha(0f);
        Fab2.setAlpha(0f);
        Fab3.setAlpha(0f);

        Fab1.setTranslationY(translationY);
        Fab2.setTranslationY(translationY);
        Fab3.setTranslationY(translationY);

        Fab1.setOnClickListener(this);
        FabMain.setOnClickListener(this);
        Fab2.setOnClickListener(this);
        Fab3.setOnClickListener(this);

    }


    @Override
    public
    void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.fabMain:
                Log.i(TAG, "onClick: Fabmenu");
                if (isMenuOpen) {
                    fab_control.closeMenu();

                } else {
                    Log.e(TAG, "onClick: exit");
                    fab_control.openMenu();
                }
                isMenuOpen = !isMenuOpen;

                break;

            case R.id.fabone:
                Log.i(TAG, "onClick: fabone is clicked");


                break;
            case R.id.fabtwo:
                Log.i(TAG, "onClick: fabtwo is clicked");
                break;
            case R.id.fabthree:
                Log.i(TAG, "onClick: fabthree is clicked");
                break;
        }


    }
}