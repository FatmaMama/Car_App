package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class AddVoiture extends AppCompatActivity {

    EditText et_vin ;
    Button next_btn;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voiture);

        et_vin = findViewById(R.id.et_vin);
        next_btn = findViewById(R.id.next_btn);

        db = FirebaseFirestore.getInstance();
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vin = et_vin.getText().toString();
                db.collection("ERP").whereEqualTo("vin", vin).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Intent intent = new Intent(getApplicationContext(), ValidAddCar.class);
                                intent.putExtra("modele", document.getData().get("modele").toString());
                                intent.putExtra("vin", document.getData().get("vin").toString());
                                startActivity(intent);
                            }
                        }
                    }
                });
            }
        });
    }
}