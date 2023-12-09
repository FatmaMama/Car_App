package com.example.car_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Accueil extends AppCompatActivity {

    ImageView details;
    Button ajt_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        details=findViewById(R.id.imageView);
        ajt_btn=findViewById(R.id.ajt_btn);

        ajt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddVoiture.class));
                finish();
            }
        });

    }
}