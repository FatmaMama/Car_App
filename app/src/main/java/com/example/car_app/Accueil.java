package com.example.car_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Accueil extends AppCompatActivity {

    ImageView details;
    Button ajtbtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        details=findViewById(R.id.imageView);
        ajtbtn=findViewById(R.id.button);



    }
}