package com.example.car_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ValidRDV extends AppCompatActivity {

    TextView mat,service,agence,date;
    Button valider,retour ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid_rdv);

        mat=findViewById(R.id.matricule);
        service=findViewById(R.id.service);
        agence=findViewById(R.id.agence);
        date=findViewById(R.id.date);
        retour=findViewById(R.id.retour);
        valider=findViewById(R.id.valider);

        Intent data = getIntent();
        String agencefav=data.getStringExtra("Agence");
        String ser =data.getStringExtra("Nom");
        String time =data.getStringExtra("Time");

        service.setText(ser);
        agence.setText(agencefav);
        date.setText(time);

        retour.setOnClickListener(v->{
            startActivity(new Intent(ValidRDV.this,DateRdvActivity.class));
        });



    }
}