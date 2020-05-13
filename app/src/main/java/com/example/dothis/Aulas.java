package com.example.dothis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Aulas extends AppCompatActivity {

    private EditText edt;
    private EditText edt2;
    private EditText edt3;
    private EditText edt4;
    private EditText edt5;
    private Button save;

    public static final String SEGUNDA = "segunda";
    public static final String TERCA = "terca";
    public static final String QUARTA = "quarta";
    public static final String QUINTA = "quinta";
    public static final String SEXTA = "sexta";

    public static final String TEXT = "text";
    public static final String TEXT2 = "text";
    public static final String TEXT3 = "text";
    public static final String TEXT4 = "text";
    public static final String TEXT5 = "text";

    private String text;
    private String text2;
    private String text3;
    private String text4;
    private String text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aulas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edt     = findViewById(R.id.edt);
        edt2     = findViewById(R.id.edt2);
        edt3     = findViewById(R.id.edt3);
        edt4     = findViewById(R.id.edt4);
        edt5     = findViewById(R.id.edt5);
        save    = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        loadData();
        updateViews();

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SEGUNDA, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences sharedPreferences2 = getSharedPreferences(TERCA, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        SharedPreferences sharedPreferences3 = getSharedPreferences(QUARTA, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        SharedPreferences sharedPreferences4 = getSharedPreferences(QUINTA, MODE_PRIVATE);
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();
        SharedPreferences sharedPreferences5 = getSharedPreferences(SEXTA, MODE_PRIVATE);
        SharedPreferences.Editor editor5 = sharedPreferences5.edit();

        editor.putString(TEXT, edt.getText().toString());
        editor.apply();
        editor2.putString(TEXT2, edt2.getText().toString());
        editor2.apply();
        editor3.putString(TEXT3, edt3.getText().toString());
        editor3.apply();
        editor4.putString(TEXT4, edt4.getText().toString());
        editor4.apply();
        editor5.putString(TEXT5, edt5.getText().toString());
        editor5.apply();

        Toast.makeText(this, R.string.salvo, Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SEGUNDA, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        SharedPreferences sharedPreferences2 = getSharedPreferences(TERCA, MODE_PRIVATE);
        text2 = sharedPreferences2.getString(TEXT2, "");
        SharedPreferences sharedPreferences3 = getSharedPreferences(QUARTA, MODE_PRIVATE);
        text3 = sharedPreferences3.getString(TEXT3, "");
        SharedPreferences sharedPreferences4 = getSharedPreferences(QUINTA, MODE_PRIVATE);
        text4 = sharedPreferences4.getString(TEXT4, "");
        SharedPreferences sharedPreferences5 = getSharedPreferences(SEXTA, MODE_PRIVATE);
        text5 = sharedPreferences5.getString(TEXT5, "");

    }

    public void updateViews(){
        edt.setText(text);
        edt2.setText(text2);
        edt3.setText(text3);
        edt4.setText(text4);
        edt5.setText(text5);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);
    }
}
