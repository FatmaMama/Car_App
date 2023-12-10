package com.example.car_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HistoriqueActivity extends AppCompatActivity {
    TextView modele, immat;
    ListView lv;
    FirebaseFirestore db;
    RdvAdapter adapter;

    String carId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        modele = findViewById(R.id.modele_hist);
        immat = findViewById(R.id.immat_hist);
        lv = findViewById(R.id.lv_hist);

        carId = getIntent().getStringExtra("carId");

        db = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        ArrayList<Rdv> rdvs = new ArrayList<>();

        adapter = new RdvAdapter(this, R.layout.rdv_item, rdvs);
        lv.setAdapter(adapter);

        getUserRdv(currentUser.getUid());
    }

    void getUserRdv(String userId){
        db.collection("RDV").whereEqualTo("user", userId).whereEqualTo("car", carId).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Rdv rdv = new Rdv(document.getData().get("service").toString(), document.getData().get("agence").toString(), document.getData().get("date").toString(),document.getData().get("heure").toString());
                        adapter.addItem(rdv);
                        adapter.notifyDataSetChanged();
                    }

                }else{
                    Toast.makeText(HistoriqueActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}