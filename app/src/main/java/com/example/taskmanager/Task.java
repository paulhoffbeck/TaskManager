package com.example.taskmanager;

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

    public String toString(){
        return id+" "+nom+" "+description;
    }
}
