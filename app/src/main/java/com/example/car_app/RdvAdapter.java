package com.example.car_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RdvAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private ArrayList<Rdv> rdvs;

    public RdvAdapter(Context c, int resource, ArrayList<Rdv> rdvs) {
        this.c = c;
        this.resource = resource;
        this.rdvs = rdvs;
    }

    public void addItem(Rdv rdv){
        this.rdvs.add(rdv);
    }

    @Override
    public int getCount() {
        return rdvs.size();
    }

    @Override
    public Rdv getItem(int i) {
        return rdvs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
            v = LayoutInflater.from(c).inflate(resource, null, false);
        }

        TextView tv_service = v.findViewById(R.id.service_hist);
        TextView tv_agence = v.findViewById(R.id.agence_hist);
        TextView tv_date = v.findViewById(R.id.date_hist);
        TextView tv_heure = v.findViewById(R.id.heure_hist);

        Rdv rdv = getItem(i);

        tv_service.setText(rdv.getService());
        tv_agence.setText(rdv.getAgence());
        tv_date.setText(rdv.getDate());
        tv_heure.setText(rdv.getHeure());

        return v;
    }
}
