package com.example.dothis.dominio.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dothis.dominio.entidades.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaRepositorio {

    private SQLiteDatabase conexao;

    public TarefaRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Tarefa tarefa){

        ContentValues contentValues = new ContentValues();
        contentValues.put("MATERIA", tarefa.materia);
        contentValues.put("TAREFA", tarefa.tarefa);
        contentValues.put("DESCRICAO", tarefa.descricao);
        contentValues.put("ENTREGA", tarefa.entrega);

        conexao.insertOrThrow("TAREFAS", null, contentValues);
    }

    public void excluir(int codigo){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        conexao.delete("TAREFAS","CODIGO = ?", parametros);

    }

    public void alterar(Tarefa tarefa){

        ContentValues contentValues = new ContentValues();
        contentValues.put("MATERIA", tarefa.materia);
        contentValues.put("TAREFA", tarefa.tarefa);
        contentValues.put("DESCRICAO", tarefa.descricao);
        contentValues.put("ENTREGA", tarefa.entrega);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(tarefa.codigo);

        conexao.update("TAREFAS", contentValues, "CODIGO = ?", parametros);
    }

    public List<Tarefa> buscarTodos(){

        List<Tarefa> tarefas = new ArrayList<Tarefa>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO, MATERIA, TAREFA, DESCRICAO, ENTREGA ");
        sql.append("FROM TAREFAS");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            do{
                Tarefa tar = new Tarefa();

                tar.codigo    = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                tar.materia   = resultado.getString(resultado.getColumnIndexOrThrow("MATERIA"));
                tar.tarefa    = resultado.getString(resultado.getColumnIndexOrThrow("TAREFA"));
                tar.descricao = resultado.getString(resultado.getColumnIndexOrThrow("DESCRICAO"));
                tar.entrega   = resultado.getString(resultado.getColumnIndexOrThrow("ENTREGA"));

                tarefas.add(tar);

            }while(resultado.moveToNext());

        }

        return tarefas;
    }

    public Tarefa buscarTarefa(int codigo){

        Tarefa tarefa = new Tarefa();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO, MATERIA, TAREFA, DESCRICAO, ENTREGA");
        sql.append("FROM TAREFAS");
        sql.append("WHERE CODIGO = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if(resultado.getCount() > 0){

            resultado.moveToFirst();

            tarefa.codigo    = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
            tarefa.materia   = resultado.getString(resultado.getColumnIndexOrThrow("MATERIA"));
            tarefa.tarefa    = resultado.getString(resultado.getColumnIndexOrThrow("TAREFA"));
            tarefa.descricao = resultado.getString(resultado.getColumnIndexOrThrow("DESCRICAO"));
            tarefa.entrega  = resultado.getString(resultado.getColumnIndexOrThrow("ENTREGA"));

            return tarefa;
        }

        return null;
    }
}
