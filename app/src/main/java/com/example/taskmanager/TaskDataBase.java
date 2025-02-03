package com.example.taskmanager;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class TaskDataBase extends SQLiteOpenHelper {

    private static TaskDataBase instance;
    private static final String nom = "TaskDataBase.db";
    private static final int version = 1;
    private Context context; //cette variable est présente pour d'éventuels Toast;
    public TaskDataBase(Context context){
        super(context,nom, null, version);
        this.context = context;
    }
    public static synchronized TaskDataBase getInstance(Context context){
        if (instance==null){
            instance = new TaskDataBase(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE my_table (id INTEGER PRIMARY KEY, name TEXT, description TEXT, date TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS my_table");
        onCreate(db);
    }

    public ArrayList<Task> affiche() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Task> liste = new ArrayList<Task>();
        Cursor recherche = db.rawQuery("SELECT id,name,description,date FROM  my_table",null);

        if (recherche != null){
            while(recherche.moveToNext()){
                int id = recherche.getInt(recherche.getColumnIndexOrThrow("id"));
                String nom = recherche.getString(recherche.getColumnIndexOrThrow("name"));
               String date = recherche.getString(recherche.getColumnIndexOrThrow("date"));
                String description = recherche.getString(recherche.getColumnIndexOrThrow("description"));
                Task tache = new Task(id,nom,description,date);
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
            tachedb.put("date", tache.date);
            db.insert("my_table", null, tachedb);
            db.close();
    }
    public void updateTask(Task tache){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tachedb = new ContentValues();
        tachedb.put("name", tache.nom);
        tachedb.put("description", tache.description);
        tachedb.put("date", tache.date);
        db.update("my_table",tachedb,"id = ?",new String[]{String.valueOf(tache.id)});
        db.close();
    }

    public void removeTask(Task tache){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("my_table", "id = ?",new String[]{String.valueOf(tache.id)});
        db.close();
    }

}
