package com.example.dothis.dominio.entidades;

import java.io.Serializable;

public class Tarefa implements Serializable {

    public int codigo;
    public String materia;
    public String tarefa;
    public String descricao;
    public String entrega;

    public Tarefa(){
        codigo = 0;
    }

}
