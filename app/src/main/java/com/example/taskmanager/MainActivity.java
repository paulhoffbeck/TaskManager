package com.example.taskmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView r1;
    ArrayList<Task>liste;
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
        TaskDataBase basedonnee = new TaskDataBase(this);
        Task ta = new Task("Test","Ceci est un test");
        basedonnee.addTask(ta);
        Task ti = new Task(1,"ReTest","ceci est encore un test");
        basedonnee.updateTask(ti);
        basedonnee.removeTask(ti);
        liste = basedonnee.affiche();
        toRecycler(liste);
    }

    void toRecycler(ArrayList<Task> liste){
        r1 =(RecyclerView) findViewById(R.id.recycl);
        r1.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter<TaskViewHolder> aptateur = new TaskAdapter(liste);
        r1.setAdapter(aptateur);
    }
}
