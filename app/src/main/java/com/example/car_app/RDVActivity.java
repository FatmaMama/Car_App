package com.example.car_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class RDVActivity extends AppCompatActivity {

    Button serv1,serv2,serv3,serv4,valider;
    int value = 0;
    String nom,desc;
    List<Object> agence;
    FirebaseFirestore fStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdvactivity);

        serv1=findViewById(R.id.serv1);
        serv2=findViewById(R.id.serv2);
        serv3=findViewById(R.id.serv3);
        serv4=findViewById(R.id.serv4);
        valider=findViewById(R.id.validerserv);

        fStore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fStore.collection("AllServices").document("D7gj4GbZI3KSTBgkIHwW");


        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    nom = documentSnapshot.getString("Nom") ;
                    desc = documentSnapshot.getString("Description");
                    agence = (List<Object>) documentSnapshot.get("Agence");

                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });


        serv1.setOnClickListener(v->{
            serv1.setBackgroundColor(Color.parseColor("#A4019D1B"));
        });
        serv2.setOnClickListener(v->{
            serv2.setBackgroundColor(Color.parseColor("#A4019D1B"));
        });
        serv3.setOnClickListener(v->{
            serv3.setBackgroundColor(Color.parseColor("#A4019D1B"));
        });
        serv4.setOnClickListener(v->{
            serv4.setBackgroundColor(Color.parseColor("#A4019D1B"));
        });

        valider.setOnClickListener(v->{
            Intent intent = new Intent(RDVActivity.this,ChoixAgence.class);
            intent.putExtra("Nom",nom);
            intent.putExtra("agence",new ArrayList<>(agence));
            startActivity(intent);

        });



    }
}