package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static android.util.Log.println;

public class textUpload extends upload {
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

                try {
                    makeTextFile(getApplicationContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                goToConfirm();
            }
        });
    }

    public void makeTextFile(Context context) throws IOException {
        File path = context.getFilesDir(); //internal storage path

        File file = new File(path, summary + ".txt"); //create File Object
        if (!file.exists()) {
            try {
                file.createNewFile(); //creates new File
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter myWriter = new FileWriter(file);

        EditText company = (EditText) findViewById(R.id.companyEditText);
        EditText docName = (EditText) findViewById(R.id.doctypeEditText);
        EditText number = (EditText) findViewById(R.id.numberEditText);
        EditText other = (EditText) findViewById(R.id.otherEditText);

        String companyStr = "Company: " + company.getText().toString() + "\n";
        String docNameStr = "Document Name: " + docName.getText().toString() + "\n";
        String numStr = "Number: " + number.getText().toString() + "\n";
        String otherStr = "Other Information: " + other.getText().toString() + "\n";

        myWriter.write(companyStr);
        myWriter.write(docNameStr);
        myWriter.write(numStr);
        myWriter.write(otherStr);

        myWriter.close();

        Log.i("file created?", file.exists() + "");

    }

    public void goToConfirm(){
        Intent intent= new Intent(this, Confirmed.class);
        startActivity(intent);
    }
}