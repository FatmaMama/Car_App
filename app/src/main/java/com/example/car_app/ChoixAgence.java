package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ChoixAgence extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap myMap;
    TextView agencefav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_agence);

        agencefav=findViewById(R.id.agencefav);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(ChoixAgence.this);



    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        float zoomLevel = 10.0f;
        LatLng ag1 = new LatLng(35.93687642505922, 10.563354411142015);
        LatLng ag2 = new LatLng(35.79442460273389,  10.83526601397979);
        LatLng ag3 = new LatLng(35.56239491686486,  11.038513070646404);
        myMap.addMarker(new MarkerOptions().position(ag1).title("Agence Hammam Sousse"));
        myMap.addMarker(new MarkerOptions().position(ag2).title("Agence Monastir "));
        myMap.addMarker(new MarkerOptions().position(ag3).title("Agence Mahdia"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(ag1));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(ag2));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(ag3));

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
        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15.0f));
        return true;
    }

}