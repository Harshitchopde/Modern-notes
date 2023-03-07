package com.example.notepad.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.OnPdfItemSelectListnear;
import com.example.notepad.R;

import java.io.File;
import java.util.List;

public
class PDFRecycleAdapter extends RecyclerView.Adapter<PDFRecycleAdapter.ViewHolder> {
    private
    Context context;
    private List<File> fileList;
    private
    OnPdfItemSelectListnear pdfItemSelectListnear;
    public
    PDFRecycleAdapter(Context context, List<File> fileList, OnPdfItemSelectListnear pdfItemSelectListnear) {
        this.context = context;
        this.fileList = fileList;
        this.pdfItemSelectListnear = pdfItemSelectListnear;
    }

    @NonNull
    @Override
    public
    ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_pdf_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public
    void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(fileList.get(position).getName());
            holder.textView.setSelected(true);
            holder.containerPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public
                void onClick(View view) {
                    pdfItemSelectListnear.onPdfClick(fileList.get(position));
                }
            });
    }

    @Override
    public
    int getItemCount() {
        return fileList.size();
    }

    public
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView containerPdf;
        public
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtpdfName);
            containerPdf = itemView.findViewById(R.id.containerPDF);
        }
    }
}
