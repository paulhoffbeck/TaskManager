package com.example.taskmanager;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskDataBase extends SQLiteOpenHelper {
    private static final String nom = "TaskDataBase.db";
    private static final int version = 1;
    private Context context;
    public TaskDataBase(Context context){
        super(context,nom, null, version);
        this.context = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE my_table (id INTEGER PRIMARY KEY, name TEXT, description TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS my_table");
        onCreate(db);
    }

    public ArrayList<Task> affiche() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Task> liste = new ArrayList<Task>();
        Cursor recherche = db.rawQuery("SELECT id,name,description FROM  my_table",null);

        if (recherche != null){
            while(recherche.moveToNext()){
                int id = recherche.getInt(recherche.getColumnIndexOrThrow("id"));
                String nom = recherche.getString(recherche.getColumnIndexOrThrow("name"));
                String description = recherche.getString(recherche.getColumnIndexOrThrow("description"));
                Task tache = new Task(id,nom,description);
                liste.add(tache);
            }
            recherche.close();
        }
        db.close();
        return liste;

    }

    public void addTask(Task tache){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues tachedb = new ContentValues();
            tachedb.put("name", tache.nom);
            tachedb.put("description", tache.description);
            long result = db.insert("my_table", null, tachedb);
            db.close();
    }

}
