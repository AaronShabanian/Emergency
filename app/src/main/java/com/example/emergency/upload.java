package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;

public class upload extends MainActivity {
    private Button toinsurance;
    private Button tosettings;
    private Button general;
    private Button banking;
    private Button will;
    private Button legal;
    public static String summary="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        toinsurance= findViewById(R.id.goto_insurance);
        toinsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myTag", "To insurance");

                openInsurance();
            }
        });
        tosettings= findViewById(R.id.tosettings);
        tosettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myTag", "To Settings");

                openSettings();
            }
        });
        general= findViewById(R.id.general);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                step("General");
            }
        });
        banking= findViewById(R.id.banking);
        banking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                step("Banking");
            }
        });
        will= findViewById(R.id.will);
        will.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                step("Will");
            }
        });
        legal= findViewById(R.id.legal);
        legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                step("Legal");
            }
        });
    }
    public void openInsurance(){
        Intent intent= new Intent(this, insurance.class);
        startActivity(intent);
    }
    public void openSettings(){
        Intent intent= new Intent(this, settings.class);
        startActivity(intent);
    }
    public void step(String type){
        summary=type;
        Intent intent= new Intent(this, choose.class);
        startActivity(intent);
    }

}
