package com.minson.learn;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);

        ProgressDialog progressDialog = new ProgressDialog(WebViewActivity.this);
        progressDialog.setTitle("This is Progressdialog");
        progressDialog.setMessage("wait for a moment");
        progressDialog.setCancelable(false);
        progressDialog.show();
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
        progressDialog.cancel();
    }

}
