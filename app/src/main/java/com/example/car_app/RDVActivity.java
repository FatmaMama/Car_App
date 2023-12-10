package com.example.car_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class RDVActivity extends AppCompatActivity {

    Button serv1,serv2,serv3,serv4,valider, retour;
    int value = 0;
    String nom,desc;
    List<Number> agence;
    FirebaseFirestore fStore;
    String result ;

    TextView modele, immat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdvactivity);

        serv1=findViewById(R.id.serv1);
        serv2=findViewById(R.id.serv2);
        serv3=findViewById(R.id.serv3);
        serv4=findViewById(R.id.serv4);
        valider=findViewById(R.id.validerserv);
        retour = findViewById(R.id.retour_btn);


        modele = findViewById(R.id.modele);
        immat = findViewById(R.id.immat);

        modele.setText(getIntent().getStringExtra("modele"));
        immat.setText(getIntent().getStringExtra("immatriculation"));

        fStore = FirebaseFirestore.getInstance();

        AtomicReference<DocumentReference> documentReference = new AtomicReference<>(fStore.collection("AllServices").document("D7gj4GbZI3KSTBgkIHwW"));

        serv1.setOnClickListener(v->{
         documentReference.set(fStore.collection("AllServices").document("3cs1VEWSxnYtcguYyKJX"));
        documentReference.get().addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    nom = documentSnapshot.getString("Nom") ;
                    desc = documentSnapshot.getString("Description");
                    agence = (List<Number>) documentSnapshot.get("Agence");
                    result = agence.stream()
                            .map(Number::toString)
                            .collect(Collectors.joining(""));
                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });
            HideColor();
            serv1.setBackgroundColor(Color.parseColor("#A4019D1B"));

        });
        serv2.setOnClickListener(v->{
            documentReference.set(fStore.collection("AllServices").document("D7gj4GbZI3KSTBgkIHwW"));
            documentReference.get().addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if(documentSnapshot.exists()){
                        nom = documentSnapshot.getString("Nom") ;
                        desc = documentSnapshot.getString("Description");
                        agence = (List<Number>) documentSnapshot.get("Agence");
                        result = agence.stream()
                                .map(Number::toString)
                                .collect(Collectors.joining(""));
                    }else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }
                }
            });
            HideColor();
            serv2.setBackgroundColor(Color.parseColor("#A4019D1B"));
        });
        serv3.setOnClickListener(v->{
            documentReference.set(fStore.collection("AllServices").document("g2ohnJBRKn3tK3lfl3Cy"));
            documentReference.get().addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if(documentSnapshot.exists()){
                        nom = documentSnapshot.getString("Nom") ;
                        desc = documentSnapshot.getString("Description");
                        agence = (List<Number>) documentSnapshot.get("Agence");
                        result = agence.stream()
                                .map(Number::toString)
                                .collect(Collectors.joining(""));
                    }else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }
                }
            });
            HideColor();
            serv3.setBackgroundColor(Color.parseColor("#A4019D1B"));
        });
        serv4.setOnClickListener(v->{
            documentReference.set(fStore.collection("AllServices").document("NLTn6rqIIHMxSBJjHIy5"));
            documentReference.get().addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                    if(documentSnapshot.exists()){
                        nom = documentSnapshot.getString("Nom") ;
                        desc = documentSnapshot.getString("Description");
                        agence = (List<Number>) documentSnapshot.get("Agence");
                        result = agence.stream()
                                .map(Number::toString)
                                .collect(Collectors.joining(""));
                    }else {
                        Log.d("tag", "onEvent: Document do not exists");
                    }
                }
            });
            HideColor();
            serv4.setBackgroundColor(Color.parseColor("#A4019D1B"));
        });


        valider.setOnClickListener(v->{
            Intent intent = new Intent(RDVActivity.this,ChoixAgence.class);
            intent.putExtra("Nom",nom);
            intent.putExtra("AgenceList", result);
            intent.putExtra("modele", modele.getText().toString());
            intent.putExtra("immatriculation", immat.getText().toString());
            intent.putExtra("carId", getIntent().getStringExtra("carId"));
            startActivity(intent);
            finish();
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RDVActivity.this, CarSpaceActivity.class));
                finish();
            }
        });

    }



    void HideColor(){
        serv1.setBackgroundColor(Color.parseColor("#1E56C9"));
        serv2.setBackgroundColor(Color.parseColor("#1E56C9"));
        serv3.setBackgroundColor(Color.parseColor("#1E56C9"));
        serv4.setBackgroundColor(Color.parseColor("#1E56C9"));
    }
}