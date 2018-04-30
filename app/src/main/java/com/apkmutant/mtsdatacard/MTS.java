package com.apkmutant.mtsdatacard;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.http.SslError;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MTS extends ActionBarActivity {
WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mts);
        wv=(WebView)findViewById(R.id.webView);


        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
        } else {

            wv = (WebView) findViewById(R.id.webView);


           wv.clearCache(true);
            wv.clearHistory();
            wv.getSettings().setJavaScriptEnabled(false);
            wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            wv.getSettings().setBuiltInZoomControls(true);

            wv.loadUrl("https://selfcare.mtsindia.in/index.html");


        }

        wv.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {


                if (!DetectConnection.checkInternetConnection(getApplicationContext())){
                    Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();
                } else {
                    view.loadUrl(url);
                }

                return true;
            }

                                @Override
                                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                                    handler.proceed();
                                }

                            }
        );
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.prakash) {

            wv.loadUrl(wv.getUrl());
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
 class DetectConnection {

    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager con_manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}


