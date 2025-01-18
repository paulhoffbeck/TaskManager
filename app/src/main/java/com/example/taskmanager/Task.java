package com.example.taskmanager;

import androidx.annotation.NonNull;

public class Task {
    String  nom, description;
    int id;
    Task(int id, String nom, String description){
        this.id =id;
        this.nom = nom;
        this.description = description;
    }

    Task(String nom, String description){
        this.id =0;
        this.nom = nom;
        this.description = description;
    }

    Task(String nom){
        this.id =0;
        this.nom = nom;
        this.description = "";
    }

    @NonNull
    public String toString(){
        return id+","+nom+","+description;
    }

    public String getNom() {
        return nom;
    }
}
