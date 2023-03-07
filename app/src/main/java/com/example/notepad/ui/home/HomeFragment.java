package com.example.notepad.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.Adapters.RecycleViewHomeAdapter;
import com.example.notepad.Database.DataBaseHelper;
import com.example.notepad.Database.Notes;
import com.example.notepad.Fab_control;
import com.example.notepad.NotesViewActivity;
import com.example.notepad.OnNotesClickListener;
import com.example.notepad.R;
import com.example.notepad.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;

public
class HomeFragment extends Fragment  {

    private FragmentHomeBinding binding;

    private static final String TAG = "HomeFragment";
    Button create_btn;
    RecyclerView recyclerView;
    DataBaseHelper dbhelper;
    ArrayList<Notes> arrayNotes;
    LinearLayout create_note;
    Fab_control fab_control;


    EditText titleIP,contentIP;
    Context context;
    Button addingmultipleNotes;

    RecycleViewHomeAdapter adapter;

    public
    View onCreateView(@NonNull LayoutInflater inflater,
                      ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        arrayNotes = new ArrayList<>();
        context = getContext();

        create_note = binding.createNotes.linearlayout;
        dbhelper = dbhelper.getDB(getContext());
        recyclerView = binding.createNotes.recycleview;
        create_btn = binding.createNotes.btuCreate;
        addingmultipleNotes = binding.createNotes.addingNotes;


        showNotes();


addingmultipleNotes.setOnClickListener(view -> {
    addingNotes();
});

create_btn.setOnClickListener(view -> {
    addingmultipleNotes.performClick();
});




        return root;
    }
    public void showSnackBar(){
        Snackbar.make(binding.rootFrameHome,"Deleted successfully",Snackbar.LENGTH_LONG).show();
    }
    public
    void showNotes() {
        arrayNotes = (ArrayList<Notes>) dbhelper.notesDAO().getALlNotes();
        if (!arrayNotes.isEmpty()) {
            create_note.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
            adapter = new RecycleViewHomeAdapter(getContext(), arrayNotes, dbhelper, new OnNotesClickListener() {
                @Override
                public
                void onNotesClick(int position) {
                    startActivity(new Intent(getContext(), NotesViewActivity.class)
                            .putExtra("word", (Serializable) arrayNotes.get(position)));
                }

                @Override
                public
                void onNotesLongClick() {
                    showNotes();
                    showSnackBar();
                }
            });
            recyclerView.setAdapter(adapter);

        } else {
            create_note.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

    }

    public
    void addingNotes() {

        Dialog dialog = new Dialog(getContext());


        dialog.setContentView(R.layout.adding_update);
        titleIP = dialog.findViewById(R.id.titleIP);
        contentIP = dialog.findViewById(R.id.contentIP);

        Button addingBtn = dialog.findViewById(R.id.addingBtn);
        addingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View view) {
                String title = titleIP.getText().toString();
                String  content  =contentIP.getText().toString();
                Log.e(TAG, "onClick: "+title+" f "+content );
                if(title.equals("")){
                    titleIP.setError("Title require");
                    return;
                }
                else if (content.equals("")){
                    contentIP.setError("content can not be empty");
                    return;
                }
                else {

                    Log.e(TAG, "onClick: before dbhelper" );
                    dbhelper.notesDAO().addNotes(new Notes(title, content));
                    Log.e(TAG, "onClick: after dbhelper" );

                    showNotes();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }





    @Override
    public
    void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}