package com.example.car_app;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class ChoixAgence extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap myMap;
    TextView agencefav,matricule,service ;
    Button retour,valider ;

    String ag,nomservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_agence);

        agencefav=findViewById(R.id.agencefav);
        matricule=findViewById(R.id.matricule);
        service=findViewById(R.id.service);
        retour=findViewById(R.id.retour);
        valider=findViewById(R.id.valider);
        Intent data = getIntent();
        nomservice=data.getStringExtra("Nom");
        service.setText(nomservice);
        ag = data.getStringExtra("AgenceList");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(ChoixAgence.this);

        retour.setOnClickListener(v->{
            startActivity(new Intent(ChoixAgence.this,RDVActivity.class));
        });
        valider.setOnClickListener(v->{
            String fav = agencefav.getText().toString();
            Intent intent = new Intent(ChoixAgence.this,DateRdvActivity.class);
            intent.putExtra("Nom",nomservice);
            intent.putExtra("Agence", fav);
            startActivity(intent);
        });


    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        LatLng ag1,ag2,ag3,ag4 ;
        ag1 = new LatLng(35.93687642505922, 10.563354411142015);
        float zoomLevel = 8.5f;
        for(int i=0;i<ag.length();i++){
            if(ag.charAt(i)== '1'){
                 ag1 = new LatLng(35.93687642505922, 10.563354411142015);
                 myMap.addMarker(new MarkerOptions().position(ag1).title("Agence Hammam Sousse"));
            } else if (ag.charAt(i)== '2') {
                 ag2 = new LatLng(35.79442460273389,  10.83526601397979);
                myMap.addMarker(new MarkerOptions().position(ag2).title("Agence Monastir "));
            } else if (ag.charAt(i)== '3') {
                 ag3 = new LatLng(35.56239491686486,  11.038513070646404);
                myMap.addMarker(new MarkerOptions().position(ag3).title("Agence Mahdia"));
            } else if (ag.charAt(i)== '4') {
                 ag4 = new LatLng(36.4983821364727,  10.803268937290119);
                myMap.addMarker(new MarkerOptions().position(ag4).title("Agence Nabeul"));
            }
        }

        myMap.moveCamera(CameraUpdateFactory.newLatLng(ag1));
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ag1, zoomLevel));

        if (myMap != null) {
            myMap.setOnMarkerClickListener(this::onMarkerClick);
            myMap.moveCamera(CameraUpdateFactory.newLatLng(ag1));
        }
    }

    public boolean onMarkerClick(Marker marker) {
        // Handle marker click here
        String title = marker.getTitle().toString();
        agencefav.setText(title);
        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 13.0f));
        return true;
    }

}