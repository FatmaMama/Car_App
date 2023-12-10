package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    EditText phone;
    TextView register ;
    Button login_btn ;
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.et_phone_login);
        register = findViewById(R.id.tv_register_login);
        login_btn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progressBar_login);

        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
                finish();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phone.getText().toString().isEmpty()) {
                    progressBar.setVisibility(View.VISIBLE);
                    login_btn.setVisibility(View.INVISIBLE);
                    sendVerificationCode(phone.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "Veuillez entrer un numéro de téléphone", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+216"+phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            progressBar.setVisibility(View.GONE);
            login_btn.setVisibility(View.VISIBLE);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            progressBar.setVisibility(View.GONE);
            login_btn.setVisibility(View.VISIBLE);
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            progressBar.setVisibility(View.GONE);
            login_btn.setVisibility(View.VISIBLE);
            Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
            intent.putExtra("telephone", phone.getText().toString());
            intent.putExtra("verificationId", verificationId);
            startActivity(intent);
            finish();
        }
    };


     @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null){
            startActivity(new Intent(LoginActivity.this, Accueil.class));
            finish();
        }
    }
}