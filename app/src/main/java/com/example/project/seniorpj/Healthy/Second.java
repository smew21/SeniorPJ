package com.example.project.seniorpj.Healthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.project.seniorpj.R;

/**
 * Created by Smew on 24/9/2560.
 */

public class Second extends AppCompatActivity {

    private WebView w1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        w1 = (WebView) findViewById(R.id.webView);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String link = intent.getStringExtra("link");

//        w1.getSettings().setJavaScriptEnabled(true);
//        w1.loadUrl("http://rssfeeds.sanook.com/rss/go/?health.index.xml|http://rssfeeds.sanook.com/rss/go/?health.health.xml|http://health.sanook.com/8277/");
//        w1.loadUrl("http://tutorialspoint.com/android/sampleXML.xml");

        w1.setWebChromeClient(new WebChromeClient());
        w1.getSettings().setJavaScriptEnabled(true);
        w1.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.w("WebActivity", "Error loading page " + description);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        w1.loadUrl(link);
    }

    @Override
    protected void onPause() {
        super.onPause();
        w1.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        w1.onResume();
    }
}
