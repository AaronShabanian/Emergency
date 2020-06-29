package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;

public class upload extends AppCompatActivity {
    private Button toinsurance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        toinsurance= (Button) findViewById(R.id.goto_insurance);
        toinsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInsurance();
            }
        });
    }
    public void openInsurance(){
        Intent intent= new Intent(this, insurance.class);
        startActivity(intent);
    }
}
