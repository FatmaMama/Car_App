package com.example.car_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText phone;
    TextView register ;
    Button sendopt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone=findViewById(R.id.telephone);
        sendopt=findViewById(R.id.sendopt);
        register=findViewById(R.id.register);

        sendopt.setOnClickListener(v->{
            String tel="+216"+phone.getText().toString();
            Intent intent = new Intent(LoginActivity.this,OPTActivity.class);
            intent.putExtra("phone",tel);
            startActivity(intent);
        });



    }
}