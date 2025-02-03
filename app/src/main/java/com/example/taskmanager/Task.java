package com.example.taskmanager;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class Task implements Comparable<Task> {
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
    @Override
    public int compareTo(Task o) {
        Comparator<Task> comparator = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.date.equals("0/0/0") && !o2.date.equals("0/0/0")) {
                    return 1;
                } else if (!o1.date.equals("0/0/0") && o2.date.equals("0/0/0")) {
                    return -1;
                } else if (o1.date.equals("0/0/0") && o2.date.equals("0/0/0")) {
                    return 0;
                }

                String[] o1st = o1.date.split("/");
                String[] o2st = o2.date.split("/");
                if(Integer.parseInt(o1st[2])<Integer.parseInt(o2st[2])){
                    return -1;
                }
                else if (Integer.parseInt(o1st[2])>Integer.parseInt(o2st[2])) {
                    return 1;
                }
                else{
                    if(Integer.parseInt(o1st[1])<Integer.parseInt(o2st[1])){
                        return -1;
                    } else if (Integer.parseInt(o1st[1])>Integer.parseInt(o2st[1])) {
                        return 1;
                    }
                    else{
                        if(Integer.parseInt(o1st[0])<Integer.parseInt(o2st[0])){
                            return -1;
                        } else if (Integer.parseInt(o1st[0])>Integer.parseInt(o2st[0])) {
                            return 1;
                        }
                        else{
                            return 0;
                        }
                    }
                }
            }
        };
        return comparator.compare(this,o);
    }
}
