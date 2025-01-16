package com.example.taskmanager;

import android.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView id,nom,descriprion;
    Task tache;
    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        id = (TextView)itemView.findViewById(R.id.id);
        nom = (TextView) itemView.findViewById(R.id.nom);
        descriprion = (TextView) itemView.findViewById(R.id.desc);
        itemView.setOnClickListener(this);
    }

public void affiche(Task tache){
    this.tache=tache;
    id.setText(this.tache.id);
    nom.setText(this.tache.nom);
    descriprion.setText(this.tache.description);

}

    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(itemView.getContext())
                .setTitle("Bouton cliqué")
                .setMessage("Liens vers le serveur dans une future realase")
                .show();
    }
    }

