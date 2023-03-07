package com.example.notepad.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.Database.DataBaseHelper;
import com.example.notepad.Database.Notes;
import com.example.notepad.OnNotesClickListener;
import com.example.notepad.R;
import com.example.notepad.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public
class RecycleViewHomeAdapter extends RecyclerView.Adapter<RecycleViewHomeAdapter.ViewHolder> {
    DataBaseHelper dataBaseHelper;
    Context context;
    ArrayList<Notes> arrayNotes;
    Toolbar toolbar;
    private
    OnNotesClickListener onNotesClickListener;

    RecycleViewHomeAdapter(Context context, ArrayList<Notes> arrayNotes, DataBaseHelper dataBaseHelper, Toolbar toolbar) {
        this.arrayNotes = arrayNotes;
        this.context = context;
        this.dataBaseHelper = dataBaseHelper;
        this.toolbar = toolbar;

    }
    public
    RecycleViewHomeAdapter(Context context, ArrayList<Notes> arrayNotes, DataBaseHelper dataBaseHelper,OnNotesClickListener onNotesClickListener) {
        this.arrayNotes = arrayNotes;
        this.context = context;
        this.dataBaseHelper = dataBaseHelper;
        this.onNotesClickListener = onNotesClickListener;

    }


    @Override
    public
    RecycleViewHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_row, parent, false);

        return  new ViewHolder(view);
    }



    @Override
    public
    void onBindViewHolder(@NonNull RecycleViewHomeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Notes model = arrayNotes.get(position);

        String title = arrayNotes.get(position).getWord();
        String detail = arrayNotes.get(position).getMeaning();
        holder.textWord.setText(title);
        holder.textMeaning.setText(detail+position);
        holder.llout.setBackgroundColor(Color.WHITE);
        holder.llout.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View view) {
                onNotesClickListener.onNotesClick(position);
            }
        });
holder.llout.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public
    boolean onLongClick(View view) {
        holder.llout.setBackgroundColor(model.isSelected()? ContextCompat.getColor(context,R.color.purple_500):Color.WHITE);
        deletingNode(model,position);
        return true;
    }
});

//        holder.textWord.setOnLongClickListener(new View.OnLongClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public
//            boolean onLongClick(View v) {
//                // check point do not pass getapplcation contxt  other wise it give null point exception
////                Toast.makeText(context, "cl", Toast.LENGTH_SHORT).show();
////                deleteHolder(position);
//
//                model.setSelected(!model.isSelected());
//                // below is for custom colour
////                holder.llout.setBackgroundColor(model.isSelected() ? ContextCompat.getColor(context,R.color.purple_500) : Color.WHITE);
//
//                holder.llout.setBackgroundColor(model.isSelected() ?Color.LTGRAY : Color.WHITE);
//
//
//                return true;
//            }
//
//
//        });


//        holder.ishare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public
//            void onClick(View v) {
//                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
//                Intent ishared = new Intent(Intent.ACTION_SEND);
//                ishared.setType("text/plain")
//                        .putExtra(Intent.EXTRA_TEXT,""+title+"\n"+detail);
//                context.startActivity(Intent.createChooser(ishared,"Share to"));
//
//            }
//        });

    }

    @Override
    public
    int getItemCount() {
        return arrayNotes.size();
    }

    public
    class ViewHolder extends RecyclerView.ViewHolder {
        // what ever you want to show declear have to declear
        TextView textWord, textMeaning;
        LinearLayout llout;
        AppCompatImageButton ishare;
        RecyclerView recyclerView;

        public
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textWord = itemView.findViewById(R.id.wordid);
            textMeaning = itemView.findViewById(R.id.meaningid);
            llout = itemView.findViewById(R.id.llrow);
            recyclerView = itemView.findViewById(R.id.recycleview);
            ishare = itemView.findViewById(R.id.shareBTN);

        }
    }
public void deletingNode(Notes note,int pos){
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure want to delete")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public
                    void onClick(DialogInterface dialogInterface, int i) {
                        dataBaseHelper.notesDAO().deleteNotes(note);
                    onNotesClickListener.onNotesLongClick();


                    }
                })
                .setNegativeButton("No",null)
                .show();


}
//    public
//    void deleteHolder(int pos) {
//        AlertDialog alertDia = new AlertDialog.Builder(context)
//                .setTitle("Delete")
//                .setMessage("Are you sure want to delete")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public
//                    void onClick(DialogInterface dialog, int which) {
//                        dataBaseHelper.notesDAO().deleteNotes(new Notes(arrayNotes.get(pos).getId(), arrayNotes.get(pos).getWord(), arrayNotes.get(pos).getMeaning()));
//                        ( context).showNotes();
//
//                    }
//                })
//                .setNegativeButton("No", null)
//                .show();
//    }



}
