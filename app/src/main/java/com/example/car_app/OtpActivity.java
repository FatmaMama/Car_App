package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OtpActivity extends AppCompatActivity {
    EditText input_code1, input_code2, input_code3, input_code4, input_code5, input_code6;
    TextView phone_text;
    ProgressBar progressBar;
    Button verifBtn;
    String verificationId;
    
    Boolean valid=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        phone_text = findViewById(R.id.phone_text);
        phone_text.setText(getIntent().getStringExtra("telephone"));

        input_code1 = findViewById(R.id.input_code1);
        input_code2 = findViewById(R.id.input_code2);
        input_code3 = findViewById(R.id.input_code3);
        input_code4 = findViewById(R.id.input_code4);
        input_code5 = findViewById(R.id.input_code5);
        input_code6 = findViewById(R.id.input_code6);
        setupOtpInputs();
        
        progressBar = findViewById(R.id.progressBar);
        verifBtn = findViewById(R.id.btn_verif);
        verificationId = getIntent().getStringExtra("verificationId");

        
        verifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(input_code1);
                checkField(input_code2);
                checkField(input_code3);
                checkField(input_code4);
                checkField(input_code5);
                checkField(input_code6);
                
                if(valid){
                    String code = input_code1.getText().toString()+input_code2.getText().toString()+input_code3.getText().toString()+
                            input_code4.getText().toString()+input_code5.getText().toString()+input_code6.getText().toString();

                    if(verificationId != null){
                        progressBar.setVisibility(View.VISIBLE);
                        verifBtn.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
                        signinByCredentials(credential);
                    }

                }else{
                    Toast.makeText(OtpActivity.this, "Veuillez entrer un code valide", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }

    void setupOtpInputs(){
        input_code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input_code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input_code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input_code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input_code5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input_code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    input_code6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }
        return valid;
    }

    private void signinByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                verifBtn.setVisibility(View.VISIBLE);
                if(task.isSuccessful()){
                    Toast.makeText(OtpActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AddVoiture.class));
                }else {
                    Toast.makeText(OtpActivity.this, "le code est invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}