package com.example.car_app;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    EditText nom, prenom, telephone;
    TextView login;
    Button registerBtn, registerPhoneBtn;
    FirebaseAuth mAuth;
    String verif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        telephone = findViewById(R.id.telephone);
        login = findViewById(R.id.login);
        registerBtn = findViewById(R.id.registerbtn);


        registerBtn.setOnClickListener(v->{
            // A completerrr
        });

        login.setOnClickListener(v->{
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        });



    }

}
