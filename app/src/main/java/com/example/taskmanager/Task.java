package com.example.taskmanager;

public class Task {
    String id, nom, description;
    Task(String id, String nom, String description){
        this.id =id;
        this.nom = nom;
        this.description = description;
    }

    public String toSting(){
        return id+" "+nom+" "+description;
    }
}
