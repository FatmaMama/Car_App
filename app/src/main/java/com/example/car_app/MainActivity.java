package com.example.car_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private  final int SPLASH_SCREEN =3000;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressBar) ;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        },SPLASH_SCREEN);

    }
}