package com.example.car_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddVoiture extends AppCompatActivity {

    EditText vin ;
    Button suivant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voiture);

        vin=findViewById(R.id.vin);
        suivant=findViewById(R.id.suivant);
    }
}