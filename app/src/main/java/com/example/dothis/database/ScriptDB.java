package com.example.dothis.database;

public class ScriptDB {

    public static String getCreateTableTarefas(){

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS TAREFAS (");
        sql.append("    CODIGO      INTEGER         PRIMARY KEY AUTOINCREMENT NOT NULL,");
        sql.append("    MATERIA     VARCHAR (100)   NOT NULL DEFAULT (''), ");
        sql.append("    TAREFA      VARCHAR (200)   NOT NULL DEFAULT (''),");
        sql.append("    DESCRICAO   VARCHAR (300)   NOT NULL DEFAULT (''),");
        sql.append("    ENTREGA     DATE            NOT NULL DEFAULT (''))");

        return sql.toString();

    }

}
