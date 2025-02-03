package com.example.taskmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskDetail extends AppCompatActivity implements View.OnClickListener {
    EditText inputnom, inputdesc;
    CalendarView inputdate;
    Task tache;
    Button modif, enleve, back;
    String dateselectionnee;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.task_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inputnom = (EditText)findViewById(R.id.editTextText3);
        inputdesc = (EditText)findViewById(R.id.editTextText2);
        inputdate = (CalendarView) findViewById(R.id.calendarView);
        dateselectionnee = "0/0/0";

        modif = (Button)findViewById(R.id.button2) ;
        enleve = (Button)findViewById(R.id.button3) ;
        back =(Button)findViewById(R.id.button4);
        modif.setOnClickListener(this);
        enleve.setOnClickListener(this);
        back.setOnClickListener(this);

        Intent i = getIntent();
        int id = i.getIntExtra("id",0);
        String nom = i.getStringExtra("nom");
        String desc = i.getStringExtra("description");
        String date = i.getStringExtra("date");
        dateselectionnee = date;
        tache = new Task(id,nom,desc,date);
        inputnom.setText(nom);
        inputdesc.setText(desc);
        try {
            inputdate.setDate(dateLong(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        inputdate.setOnDateChangeListener(((view, year, month, dayOfMonth) -> {
            month++;
            dateselectionnee = dayOfMonth+"/"+month+"/"+year;
        }));
    }

    @Override
    public void onClick(View v) {
        if(String.valueOf(inputnom.getText()).equals("")){
            Toast.makeText(this, getString(R.string.erreur2), Toast.LENGTH_SHORT).show();
        }
        else{
            tache.nom = String.valueOf(inputnom.getText());
            tache.description = String.valueOf(inputdesc.getText());
            tache.date = dateselectionnee;
            int idbtn = v.getId();
            TaskDataBase basedonnee = TaskDataBase.getInstance(this);
            if (idbtn == R.id.button2){
                basedonnee.updateTask(tache);
                Toast.makeText(this, getString(R.string.info1), Toast.LENGTH_SHORT).show();

            } else if (idbtn == R.id.button3) {
                basedonnee.removeTask(tache);
                Toast.makeText(this, getString(R.string.info2), Toast.LENGTH_SHORT).show();
                Intent i3 = new Intent();
                setResult(AppCompatActivity.RESULT_OK,i3);
                finish();
            }
            else if (idbtn == R.id.button4){
                Intent i3 = new Intent();
                setResult(AppCompatActivity.RESULT_OK,i3);
                finish();
            }
        }
    }
    long dateLong(String date) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Date longdate = sd.parse(date);
        return longdate.getTime();
    }
}