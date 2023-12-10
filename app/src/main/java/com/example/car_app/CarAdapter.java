package com.example.car_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CarAdapter extends BaseAdapter {
    private Context c;
    private int resource;
    private ArrayList<Car> cars;

    public CarAdapter(Context c, int resource, ArrayList<Car> cars) {
        this.c = c;
        this.resource = resource;
        this.cars = cars;
    }

    public void addItem(Car car){
        this.cars.add(car);
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Car getItem(int i) {
        return cars.get(i);
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

        TextView tv_modele = v.findViewById(R.id.tv_modele);
        TextView tv_immat = v.findViewById(R.id.tv_immat);

        Car car = getItem(i);

        tv_modele.setText(car.getModele());
        tv_immat.setText(car.getImmatriculation());

        return v;
    }
}
