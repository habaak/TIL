package com.example.student.p427;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    WebView wv;
    Button button,button2;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        wv = findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient());
        wv.addJavascriptInterface(new JS(),"js");

        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        handler = new Handler();
    }
    public void clickBt(View v){
        wv.loadUrl("http://70.12.114.132/ad/sample.html");
    }
    public void clickBt2(View v){
        handler.post(new Runnable() {
            @Override
            public void run() {
                wv.loadUrl("javascript:changeImg()");
            }
        });
    }
    final class JS{
        JS(){}
        @android.webkit.JavascriptInterface
        public void clickJS(String i){
            tv.setText(i);
            Log.d("[ JS ]","Event Process..."+i);
        }
    }
}
