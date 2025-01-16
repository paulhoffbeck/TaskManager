package com.example.taskmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends  RecyclerView.Adapter<TaskViewHolder>{
    ArrayList<Task> liste;

    TaskAdapter(ArrayList<Task> liste){
        if (liste != null){
            this.liste=liste;
        }
        else{
            this.liste=new ArrayList<Task>();
        }
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task tache =liste.get(position);
        holder.affiche(tache);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }
}
