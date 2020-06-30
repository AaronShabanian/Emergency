package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;

public class insurance extends upload {
    private Button home;
    private Button car;
    private Button life;
    private Button other;
    private Button medical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insurance);
        home= findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next("Home Insurance");
            }
        });
        life= findViewById(R.id.life);
        life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next("Life Insurance");
            }
        });
        medical= findViewById(R.id.medical);
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next("Medical Insurance");
            }
        });
        car= findViewById(R.id.car);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next("Car Insurance");
            }
        });
        other= findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next("Other Insurance");
            }
        });
    }
    public void next(String type){
        summary=type;
        Log.i("mytag",summary);
        Intent intent= new Intent(this, choose.class);
        startActivity(intent);
    }
}