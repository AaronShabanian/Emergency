package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DriveUpload extends AppCompatActivity {

    private WebView drive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_upload);
        drive=findViewById(R.id.drive);
        WebSettings webSettings = drive.getSettings();
        webSettings.setJavaScriptEnabled(true);
        drive.setWebViewClient(new WebViewClient());
        drive.loadUrl("https://m.box.com/browse/0/upload");
    }
}