package com.example.dothis;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Info extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    TextView txt4;
    Button horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt4 = (TextView) findViewById(R.id.txt4);
        horario = (Button) findViewById(R.id.horario);

        String vName = BuildConfig.VERSION_NAME;
        txt4.setText("App Version: " + vName);

        horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "timepicker");
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        //Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(System.currentTimeMillis());
        cal2.set(Calendar.HOUR_OF_DAY, hour);
        cal2.set(Calendar.MINUTE, minute);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        setAlarm(cal2);

        String time;
        if(hour<10 && minute<10){
            time = "0" + hour + ":" + "0" + minute;
            horario.setText(time);
        }
        if(hour<10 && minute>10){
            time = "0" + hour + ":" + minute;
            horario.setText(time);
        }
        if(hour>10 && minute<10){
            time = hour + ":" + "0" + minute;
            horario.setText(time);
        }
        if(hour>10 && minute>10){
            time = hour + ":" + minute;
            horario.setText(time);
        }

    }

    private void setAlarm(Calendar cal3){
        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal3.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);
    }

}
