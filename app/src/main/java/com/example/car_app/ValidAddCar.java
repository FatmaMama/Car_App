package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ValidAddCar extends AppCompatActivity {
    TextView vin, modele;
    EditText kilometrage;
    Button ajout_btn;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid_add_car);

        vin = findViewById(R.id.vin);
        modele = findViewById(R.id.modele);
        kilometrage = findViewById(R.id.kilometrage);
        ajout_btn = findViewById(R.id.ajout_btn);

        vin.setText(getIntent().getStringExtra("vin"));
        modele.setText(getIntent().getStringExtra("modele"));

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        Map<String, Object> car = new HashMap<>();
        car.put("vin", getIntent().getStringExtra("vin"));
        car.put("modele", getIntent().getStringExtra("modele"));
        car.put("kilometrage", kilometrage.getText().toString());
        car.put("user", currentUser.getUid());
        ajout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("cars").document().set(car).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ValidAddCar.this, "Véhicule Ajouté", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Accueil.class));
                        }else {
                            Toast.makeText(ValidAddCar.this, "Ajout échoué", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}