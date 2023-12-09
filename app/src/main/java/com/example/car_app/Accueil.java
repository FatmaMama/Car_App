package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {
    Button ajt_btn, carDetails_btn ;
    ListView lv;
    CarAdapter adapter;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        ajt_btn=findViewById(R.id.ajt_btn);
        lv = findViewById(R.id.lv);

        db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        ArrayList<Car> cars = new ArrayList<>();

        adapter = new CarAdapter(this, R.layout.car_item, cars);
        lv.setAdapter(adapter);

        getUserCars(currentUser.getUid());
        ajt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddVoiture.class));
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Car car = adapter.getItem(position);
                            Intent intent = new Intent(getBaseContext(), RDVActivity.class);
                            intent.putExtra("modele", car.getModele());
                            intent.putExtra("immatriculation", car.getImmat());
                            intent.putExtra("id", car.getId());
                            startActivity(intent);
                /*Log.i("TAG", "onItemClick: "+adapter.getCount());*/
            }
        });
    }

    void getUserCars(String userId){
        db.collection("cars").whereEqualTo("user", userId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Car car = new Car(document.getData().get("modele").toString(), document.getData().get("immatriculation").toString(), document.getId());
                        adapter.addItem(car);
                        adapter.notifyDataSetChanged();
                    }

                }else{
                    Toast.makeText(Accueil.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}