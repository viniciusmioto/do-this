package com.example.dothis.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import android.support.annotation.Nullable;

public class DadosTarefas extends SQLiteOpenHelper{


    public DadosTarefas(@Nullable Context context) {
        super(context, "TAREFAS", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDB.getCreateTableTarefas());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

}