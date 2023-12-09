package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateRdvActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView datechoisi,servicechoisi,agencefav;
    Button valider,retour,time1,time2,time3,time4 ;
    Calendar calendar;
    String resultat ;
    String dateTime ;
    String ch="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_rdv);

        retour=findViewById(R.id.retour);
        valider=findViewById(R.id.valider);
        time1=findViewById(R.id.time1);
        time2=findViewById(R.id.time2);
        time3=findViewById(R.id.time3);
        time4=findViewById(R.id.time4);
        agencefav=findViewById(R.id.agence);
        servicechoisi=findViewById(R.id.service);


        Intent data = getIntent();
        String service = data.getStringExtra("Nom");
        String agence = data.getStringExtra("Agence");

        servicechoisi.setText(service);
        agencefav.setText(agence);


        calendarView=findViewById(R.id.calendar);
        datechoisi=findViewById(R.id.datechoisi);
        calendar=Calendar.getInstance();


        time1.setOnClickListener(v->{
            HideColor();
            time1.setBackgroundColor(Color.parseColor("#A4019D1B"));
            ch = time1.getText().toString() ;
        });
        time2.setOnClickListener(v->{
            HideColor();
            time2.setBackgroundColor(Color.parseColor("#A4019D1B"));
            ch = time2.getText().toString() ;
        });
        time3.setOnClickListener(v->{
            HideColor();
            time3.setBackgroundColor(Color.parseColor("#A4019D1B"));
            ch = time3.getText().toString() ;
        });
        time4.setOnClickListener(v->{
            HideColor();
            time4.setBackgroundColor(Color.parseColor("#A4019D1B"));
             ch = time4.getText().toString() ;
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, day);

                // Use SimpleDateFormat to get the day name
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
                String dayName = dateFormat.format(selectedDate.getTime());

                resultat = ch.concat(String.valueOf(day).concat("/").concat(String.valueOf(month)).concat("/").concat(String.valueOf(year)));
                dateTime=dayName.concat(" : ").concat(resultat);
                datechoisi.setText(dateTime);
            }
        });

        valider.setOnClickListener(v->{
            Intent intent = new Intent(DateRdvActivity.this,ValidRDV.class);
            intent.putExtra("Nom",service);
            intent.putExtra("Agence", agence);
            intent.putExtra("Time", dateTime);
            startActivity(intent);
        });
    }

    public void setdate(int d,int m,int y){
        calendar.set(Calendar.DAY_OF_MONTH,d);
        calendar.set(Calendar.MONTH,m);
        calendar.set(Calendar.YEAR,y);
    }
    void HideColor(){
        time1.setBackgroundColor(Color.parseColor("#1E56C9"));
        time2.setBackgroundColor(Color.parseColor("#1E56C9"));
        time3.setBackgroundColor(Color.parseColor("#1E56C9"));
        time4.setBackgroundColor(Color.parseColor("#1E56C9"));
    }
}