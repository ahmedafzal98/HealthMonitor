package com.example.tablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web_view extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String url = getIntent().getStringExtra("web_url");

        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new myBrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
    public class myBrowser extends WebViewClient
    {

    }
}
