package com.example.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class access extends AppCompatActivity {
    private WebView driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        driver=findViewById(R.id.gDrive);
        WebSettings webSettings = driver.getSettings();
        webSettings.setJavaScriptEnabled(true);
        driver.setWebViewClient(new WebViewClient());
        driver.loadUrl("https://drive.google.com/drive/u/0/my-drive");
    }
}