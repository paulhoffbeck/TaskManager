package com.example.taskmanager;

import androidx.annotation.NonNull;

public class Task {
    String  nom, description, date;
    int id;
    Task(int id, String nom, String description, String date){
        this.id =id;
        this.nom = nom;
        this.description = description;
        this.date = date;
    }


    Task(String nom){
        this.id =0;
        this.nom = nom;
        this.description = "";
        this.date = "0/0/0";
    }

    @NonNull
    public String toString(){
        return id+","+nom+","+description+","+date;
    }
}
