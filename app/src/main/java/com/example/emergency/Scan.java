package com.example.emergency;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Scan extends choose {
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        start= findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myTag", "Data Saved");
                captureScan();
                //starts();
            }
        });
    }

    public void captureScan() {
        Intent intent = new Intent(this, cameraScan.class);
        startActivity(intent);

    }

    public void starts(){
        Intent intent= new Intent(this, DriveUpload.class);
        startActivity(intent);
    }
}