package com.example.finalexam;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private Cursor _cu;
    private ArrayList<Task> _tasks;
    private OnNoteListener mOnNoteListener;
    public RecyclerAdapter(Context c, Cursor cu, ArrayList<Task> tasks, OnNoteListener onNoteListener) {
        this.context = c;
        this._cu = cu;
        this._tasks = tasks;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.row, parent, false);
        return new ViewHolder(v,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder._tv1.setText(_cu.getString(position));
        holder._tv2.setText(_cu.getString(position));
        if(_cu.getString(position).equals("1")){
            holder._cb.setChecked(true);
        }else {
            holder._cb.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return _cu.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView _tv1, _tv2;
        private CheckBox _cb;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            _tv1 = itemView.findViewById(R.id.nomrow);
            _tv2 = itemView.findViewById(R.id.date);
            _cb = itemView.findViewById(R.id.checkbox);
             this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}







