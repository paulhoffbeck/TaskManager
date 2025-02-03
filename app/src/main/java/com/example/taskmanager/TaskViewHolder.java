package com.example.taskmanager;

import static android.app.ProgressDialog.show;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;
import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ActivityResultLauncher<Intent> activityResultLauncher;
    Context context;
    TextView id, nom, descriprion,date;
    Task tache;
    Activity acti;

    public TaskViewHolder(@NonNull View itemView, Activity acti) {
        super(itemView);
        this.acti = acti;
        date = (TextView)itemView.findViewById(R.id.date_viewholder);
        id = (TextView) itemView.findViewById(R.id.id);
        nom = (TextView) itemView.findViewById(R.id.nom);
        descriprion = (TextView) itemView.findViewById(R.id.desc);
        itemView.setOnClickListener(this);
        context = itemView.getContext();

    }

    public void affiche(Task tache) {
        this.tache = tache;
        id.setText(String.valueOf(this.tache.id));
        nom.setText(this.tache.nom);
        descriprion.setText(this.tache.description);
        date.setText(this.tache.date);
        if (String.valueOf(date.getText()).equals("0/0/0")){
            date.setBackgroundColor(Color.RED);
            date.setTextColor(Color.WHITE);
        }
    }



    @Override
    public void onClick(View v) {

        int intid = Integer.parseInt(id.getText().toString());
        String stringnom = String.valueOf(nom.getText().toString());
        String stringdesc = String.valueOf(descriprion.getText().toString());
        String stringdate = String.valueOf(date.getText().toString());
        Intent i2 = new Intent(context,TaskDetail.class);
        i2.putExtra("id",intid);
        i2.putExtra("nom",stringnom);
        i2.putExtra("description",stringdesc);
        i2.putExtra("date",stringdate);
        acti.startActivityForResult(i2,5);
        ;
    }
}

