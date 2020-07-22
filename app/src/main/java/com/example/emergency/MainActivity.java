package com.example.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button access;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.Personal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myTag", "This is my message");
                openPersonal();
            }
        });
        access= (Button) findViewById(R.id.kin);
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myTag", "This is my message");
                openkin();
            }
        });
    }
    public void openPersonal(){
        Intent intent= new Intent(this, upload.class);
        startActivity(intent);
    }
    public void openkin(){
        Intent intent= new Intent(this, access.class);
        startActivity(intent);
    }
}
