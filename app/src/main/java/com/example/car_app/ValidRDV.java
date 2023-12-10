package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ValidRDV extends AppCompatActivity {

    TextView modele,immat,service,agence,date,heure;
    Button valider,retour ;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid_rdv);

        modele = findViewById(R.id.modele_rdv);
        immat=findViewById(R.id.immat_rdv);
        service=findViewById(R.id.service_rdv);
        agence=findViewById(R.id.agence_rdv);
        date=findViewById(R.id.date_rdv);
        heure=findViewById(R.id.heure_rdv);
        retour=findViewById(R.id.retour_rdv);
        valider=findViewById(R.id.valider_rdv);

        Intent data = getIntent();
        modele.setText(data.getStringExtra("modele"));
        immat.setText(data.getStringExtra("immatriculation"));
        service.setText(data.getStringExtra("service"));
        agence.setText(data.getStringExtra("agence"));
        date.setText(data.getStringExtra("date"));
        heure.setText(data.getStringExtra("heure"));

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        retour.setOnClickListener(v->{
            startActivity(new Intent(ValidRDV.this,DateRdvActivity.class));
            finish();
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRDV(currentUser.getUid());
            }
        });

    }

    void addRDV(String userId){
        Map<String, Object> rdv = new HashMap<>();
        rdv.put("car", getIntent().getStringExtra("carId"));
        rdv.put("service", getIntent().getStringExtra("service"));
        rdv.put("agence", getIntent().getStringExtra("agence"));
        rdv.put("date", getIntent().getStringExtra("date"));
        rdv.put("heure", getIntent().getStringExtra("heure"));
        rdv.put("user", userId);

        db.collection("RDV").document().set(rdv).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ValidRDV.this, "Rendez_vous Ajouté", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Accueil.class));
                    finish();
                }else {
                    Toast.makeText(ValidRDV.this, "Ajout échoué", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}