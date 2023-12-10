package com.example.car_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CarSpaceActivity extends AppCompatActivity {
    Button historique_btn, rdv_btn, rec_btn, retour_btn;
    TextView modele, immat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_space);

        modele = findViewById(R.id.modele_car);
        immat = findViewById(R.id.immat_car);
        historique_btn = findViewById(R.id.historique_btn);
        rdv_btn = findViewById(R.id.rdv_btn);
        rec_btn = findViewById(R.id.reclamation_btn);
        retour_btn = findViewById(R.id.retour_car);

        modele.setText(getIntent().getStringExtra("modele"));
        immat.setText(getIntent().getStringExtra("immatriculation"));
        rdv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RDVActivity.class);
                intent.putExtra("modele", modele.getText().toString());
                intent.putExtra("immatriculation", immat.getText().toString());
                intent.putExtra("carId", getIntent().getStringExtra("carId"));
                startActivity(intent);
            }
        });

        historique_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HistoriqueActivity.class);
                intent.putExtra("modele", modele.getText().toString());
                intent.putExtra("immatriculation", immat.getText().toString());
                intent.putExtra("carId", getIntent().getStringExtra("carId"));
                startActivity(intent);
            }
        });

        rec_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        retour_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Accueil.class));
            }
        });

    }
}