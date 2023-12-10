package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateRdvActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView datechoisi,modele, immat,service,agence;
    Button valider,retour,time1,time2,time3,time4 ;
    Calendar calendar;
    String resultat ;
    String dateTime ;
    String ch="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_rdv);

        retour=findViewById(R.id.retour_dt);
        valider=findViewById(R.id.valider_dt);
        time1=findViewById(R.id.time1);
        time2=findViewById(R.id.time2);
        time3=findViewById(R.id.time3);
        time4=findViewById(R.id.time4);
        agence=findViewById(R.id.agence_dt);
        service=findViewById(R.id.service_dt);
        modele = findViewById(R.id.modele_dt);
        immat = findViewById(R.id.immat_dt);

        Intent data = getIntent();
        modele.setText(data.getStringExtra("modele"));
        immat.setText(data.getStringExtra("immatriculation"));
        service.setText(data.getStringExtra("Nom"));
        agence.setText(data.getStringExtra("Agence"));


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
            intent.putExtra("service",service.getText().toString());
            intent.putExtra("agence", agence.getText().toString());
            intent.putExtra("modele",modele.getText().toString());
            intent.putExtra("immatriculation", immat.getText().toString());
            intent.putExtra("date", dateTime);
            intent.putExtra("heure", ch);
            intent.putExtra("carId", getIntent().getStringExtra("carId"));
            startActivity(intent);
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DateRdvActivity.this, ChoixAgence.class));
            }
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