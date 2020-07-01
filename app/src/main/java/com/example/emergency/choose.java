package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class choose extends AppCompatActivity {
    private Button texts;
    private Button scanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        texts= findViewById(R.id.text);
        texts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totext();
            }
        });
        scanner= findViewById(R.id.scanner);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toscan();
            }
        });
    }
    public void totext(){
        Intent intent= new Intent(this, textUpload.class);
        startActivity(intent);
    }
    public void toscan(){
        Intent intent= new Intent(this, Scan.class);
        startActivity(intent);
    }
}