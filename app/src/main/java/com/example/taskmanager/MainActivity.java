package com.example.taskmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Activity acti;
    Button btn;
    EditText e1;
    RecyclerView r1;
    ArrayList<Task>liste;
    TaskDataBase basedonnee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        acti = this;
        btn = (Button)findViewById(R.id.button);
        e1 =(EditText)findViewById(R.id.editTextText);
        btn.setOnClickListener(this);

        basedonnee = TaskDataBase.getInstance(this);
        liste = basedonnee.affiche();
        toRecycler(liste);
    }
    void toRecycler(ArrayList<Task> liste){
        if(liste.size()==0){
            liste.add(new Task(0,getString(R.string.default1),getString(R.string.default2),"01/09/2027"));
            liste.add(new Task(5,getString(R.string.default21),getString(R.string.default22),"05/11/1605"));
            liste.add(new Task(6,"Information",getString(R.string.alert1),"0/0/0"));
            r1 =(RecyclerView) findViewById(R.id.recycl);
            r1.setLayoutManager(new LinearLayoutManager(this));
            RecyclerView.Adapter<TaskViewHolder> aptateur = new TaskAdapter(liste,acti);
            r1.setAdapter(aptateur);
        }
        else{
            r1 =(RecyclerView) findViewById(R.id.recycl);
            r1.setLayoutManager(new LinearLayoutManager(this));
            RecyclerView.Adapter<TaskViewHolder> aptateur = new TaskAdapter(liste,acti);
            r1.setAdapter(aptateur);
        }
    }
    @Override
    public void onClick(View v) {
        if(e1.length()==0){
            Toast.makeText(this, getString(R.string.erreur1), Toast.LENGTH_SHORT).show();
        }
        else{
            String nom = String.valueOf(e1.getText());
            Task tache = new Task(nom);
            basedonnee.addTask(tache);
            liste =basedonnee.affiche();
            toRecycler(liste);
            Task tache2 = liste.get(liste.size()-1);
            Intent i2 = new Intent(this,TaskDetail.class);
            i2.putExtra("id",tache2.id);
            i2.putExtra("nom",tache2.nom);
            i2.putExtra("description",tache2.description);
            i2.putExtra("date",tache2.date);
            startActivityForResult(i2,5);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==5){
            liste = basedonnee.affiche();
            Collections.sort(liste);
            toRecycler(liste);
            e1.setText("");
        }
    }
}
