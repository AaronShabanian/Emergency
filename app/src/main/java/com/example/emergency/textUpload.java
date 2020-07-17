package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import com.box.androidsdk.content.BoxApiFile;
import com.box.androidsdk.content.BoxApiFolder;
import com.box.androidsdk.content.BoxConfig;
import com.box.androidsdk.content.BoxConstants;
import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.BoxFutureTask;
import com.box.androidsdk.content.auth.BoxAuthentication;
import com.box.androidsdk.content.models.BoxEntity;
import com.box.androidsdk.content.models.BoxError;
import com.box.androidsdk.content.models.BoxFile;
import com.box.androidsdk.content.models.BoxFolder;
import com.box.androidsdk.content.models.BoxItem;
import com.box.androidsdk.content.models.BoxIteratorItems;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.requests.BoxRequestsFile;
import com.box.androidsdk.content.requests.BoxResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.net.HttpURLConnection;

import static android.util.Log.println;

public class textUpload extends upload {
    BoxSession mSession = null;
    private Button save;
    private BoxApiFile mFileApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_upload);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
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
        String absolute = context.getFilesDir().getAbsolutePath();
        File file = new File(path, summary + ".txt"); //create File Object
        if (!file.exists()) {
            try {
                file.createNewFile(); //creates new File
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter myWriter = new FileWriter(file);

        EditText company =findViewById(R.id.companyEditText);
        EditText docName =findViewById(R.id.doctypeEditText);
        EditText number = findViewById(R.id.numberEditText);
        EditText other =findViewById(R.id.otherEditText);
        String companyStr = "Company: " + company.getText().toString() + "\n";
        String docNameStr = "Document Name: " + docName.getText().toString() + "\n";
        String numStr = "Number: " + number.getText().toString() + "\n";
        String otherStr = "Other Information: " + other.getText().toString() + "\n";
        Log.i("Company",companyStr);
        myWriter.write(companyStr);
        myWriter.write(docNameStr);
        myWriter.write(numStr);
        myWriter.write(otherStr);

        myWriter.close();

        Log.i("Path", absolute);
        InputStream uploadStream = new FileInputStream(
                file);
        byte[] buffer = new byte[uploadStream.available()];
        uploadStream.read(buffer);
        String uploadName = summary+".txt";
        BoxConfig.CLIENT_ID = "eamauh3g5ff0dggp0geew0c2jme1vpwg";
        BoxConfig.CLIENT_SECRET = "iMBBMDrz5quVYyIxxnFh9yJmEJtzJm2u";
        BoxConfig.REDIRECT_URL = "https://localhost";
        BoxSession session = new BoxSession(textUpload.this);
        session.authenticate();
        Log.i("auth","Authenticated");
        BoxApiFile fileApi = new BoxApiFile(session);
        Log.i("Path", "Done");
        String destinationFolderId = "0";
        try {
            BoxRequestsFile.UploadFile request = fileApi.getUploadRequest(uploadStream, uploadName, destinationFolderId);
            final BoxFile uploadFileInfo = request.send();
            Log.i("Success", "Uploaded " + uploadFileInfo.getName());
        } catch (BoxException e) {
            e.printStackTrace();
            Log.w("Fail", "Failed");
        }
    }
    public void goToConfirm(){
        Intent intent= new Intent(this, Confirmed.class);
        startActivity(intent);
    }
}