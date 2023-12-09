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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    EditText input_code1, input_code2, input_code3, input_code4, input_code5, input_code6;
    TextView phone_text, resendOtp;
    ProgressBar progressBar;
    Button verifBtn;
    String verificationId;
    Boolean valid=true;
    FirebaseFirestore db;
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

        resendOtp = findViewById(R.id.resendopt);

        progressBar = findViewById(R.id.progressBar);
        verifBtn = findViewById(R.id.btn_verif);
        verificationId = getIntent().getStringExtra("verificationId");

        db = FirebaseFirestore.getInstance();
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

        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode(getIntent().getStringExtra("telephone"));
            }
        });
        
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                        .setPhoneNumber("+216"+phoneNumber)
                        .setTimeout(30L, TimeUnit.SECONDS)
                        .setActivity(this)

                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String newVerificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            verificationId = newVerificationId;
            Toast.makeText(OtpActivity.this, "Code envoyé", Toast.LENGTH_SHORT).show();
        }
    };

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
        Map<String, Object> userP = new HashMap<>();
        userP.put("nom", getIntent().getStringExtra("nom"));
        userP.put("prenom", getIntent().getStringExtra("prenom"));
        userP.put("telephone", getIntent().getStringExtra("telephone"));
        fAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                verifBtn.setVisibility(View.VISIBLE);
                if(task.isSuccessful()){
                    FirebaseUser user = task.getResult().getUser();
                    db.collection("Users").document(user.getUid()).set(userP).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(OtpActivity.this, "Vous êtes connecté", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Accueil.class));
                            }
                        }
                    });
                }else {
                    Toast.makeText(OtpActivity.this, "le code est invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}