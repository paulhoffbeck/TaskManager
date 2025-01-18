package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityResultLauncher<Intent> activityResultLauncher;
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
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Toast.makeText(this, "Tache modifiée", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        btn = (Button)findViewById(R.id.button);
        e1 =(EditText)findViewById(R.id.editTextText);
        btn.setOnClickListener(this);

        basedonnee = new TaskDataBase(this);
        liste = basedonnee.affiche();
        toRecycler(liste);
    }

    void toRecycler(ArrayList<Task> liste){
        r1 =(RecyclerView) findViewById(R.id.recycl);
        r1.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter<TaskViewHolder> aptateur = new TaskAdapter(liste,activityResultLauncher);
        r1.setAdapter(aptateur);
    }

    @Override
    public void onClick(View v) {
        if(e1.length()==0){
            Toast.makeText(this, "Veillez remplir le nom", Toast.LENGTH_SHORT).show();
        }
        else{
            String nom = String.valueOf(e1.getText());
            Task tache = new Task(nom);
            basedonnee.addTask(tache);
            liste = basedonnee.affiche();
            toRecycler(liste);
        }


    }
}
