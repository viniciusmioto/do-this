package com.example.dothis;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.dothis.database.DadosTarefas;
import com.example.dothis.dominio.entidades.Tarefa;
import com.example.dothis.dominio.repositorios.TarefaRepositorio;

import java.text.DateFormat;
import java.util.Calendar;

public class Cadastro extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText edt_materia;
    private EditText edt_tarefa;
    private EditText edt_descricao;
    private Button   btn_entrega;
    private DatePickerDialog.OnDateSetListener setDate;

    private SQLiteDatabase conexao;
    private DadosTarefas dadosTarefas;
    private TarefaRepositorio tarefaRepositorio;
    private ConstraintLayout layout_cadastro;

    private Tarefa tare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edt_materia = (EditText) findViewById(R.id.edt_materia);
        edt_tarefa = (EditText) findViewById(R.id.edt_tarefa);
        edt_descricao = (EditText) findViewById(R.id.edt_descricao);
        btn_entrega = (Button) findViewById(R.id.btn_entrega);
        layout_cadastro = (ConstraintLayout) findViewById(R.id.layout_cadastro);

        btn_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        criarConexao();
        verificaParametro();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String date = DateFormat.getDateInstance().format(c.getTime());
        btn_entrega.setText(date);
    }

    private void verificaParametro(){

        Bundle bundle = getIntent().getExtras();

        tare = new Tarefa();

        if((bundle != null) && (bundle.containsKey("TAREFA"))){

            tare = (Tarefa)bundle.getSerializable("TAREFA");

            edt_materia.setText(tare.materia);
            edt_tarefa.setText(tare.tarefa);
            edt_descricao.setText(tare.descricao);
            btn_entrega.setText(tare.entrega);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu); //criando o menu_cadastro

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case android.R.id.home:
                finish();
                break;

            case R.id.act_ok:
                confirmar();
                break;

            case R.id.act_excluir:
                tarefaRepositorio.excluir(tare.codigo);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);
    }

    private void criarConexao(){

        try{

            dadosTarefas = new DadosTarefas(this);
            conexao = dadosTarefas.getWritableDatabase();
            //Snackbar.make(layout_cadastro, R.string.msg_conexao, Snackbar.LENGTH_SHORT)
              //      .setAction(R.string.msg_ok, null).show();

            tarefaRepositorio = new TarefaRepositorio(conexao);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.msg_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.msg_ok, null);
            dlg.show();

        }
    }

    private void confirmar(){


        if(validaCampos() == false){

            try {

                if (tare.codigo == 0){
                    tarefaRepositorio.inserir(tare);
                }
                else{
                    tarefaRepositorio.alterar(tare);
                }

                finish();

            }catch (SQLException ex){

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.msg_erro);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.msg_ok, null);
                dlg.show();

            }
        }
    }

    private boolean validaCampos() {

        boolean res = false;

        String materia = edt_materia.getText().toString();
        String tarefa = edt_tarefa.getText().toString();
        String descricao = edt_descricao.getText().toString();
        String entrega = btn_entrega.getText().toString();

        tare.materia   = materia;
        tare.tarefa    = tarefa;
        tare.descricao = descricao;
        tare.entrega   = entrega;


        if (isCampoVazio(materia)) {
            edt_materia.requestFocus();
            res = true;
        } else if (isCampoVazio(tarefa)) {
            edt_tarefa.requestFocus();
            res = true;
        } else if (isCampoVazio(descricao)) {
            edt_descricao.requestFocus();
            res = true;
        } else if (isCampoVazio(entrega)) {
            btn_entrega.requestFocus();
            res = true;
        }

        if (res==true) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.aviso);
            dlg.setMessage(R.string.msg_aviso);
            dlg.setNeutralButton(R.string.msg_ok, null);
            dlg.show();
        }
        return res;
    }

    private boolean isCampoVazio(String valor) {
        boolean ata = (TextUtils.isEmpty(valor) || valor.trim().isEmpty()); //vericando campos vazios
        return ata;
    }

    private void voltar() {
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);
    }

}
