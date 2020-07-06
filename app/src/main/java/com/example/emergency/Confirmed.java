package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class Confirmed extends AppCompatActivity {
    private Button tohome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed);
        tohome= findViewById(R.id.home);
        tohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myTag", "To home");

                gohome();
            }
        });
    }
    public void gohome(){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}