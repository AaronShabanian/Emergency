package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class textUpload extends choose {
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_upload);
        save= findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myTag", "Data Saved");

                saves();
            }
        });
    }
    public void saves(){
        Intent intent= new Intent(this, Confirmed.class);
        startActivity(intent);
    }
}