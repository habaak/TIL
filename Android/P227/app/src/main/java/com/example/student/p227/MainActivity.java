package com.example.student.p227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    LinearLayout ll;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeui();

    }
    public void makeui(){
        wv=findViewById(R.id.vw);
        ll=findViewById(R.id.ll);
        iv=findViewById(R.id.iv);

        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);

        wv.setVisibility(View.INVISIBLE);
        ll.setVisibility(View.INVISIBLE);
        iv.setVisibility(View.INVISIBLE);
    }
    public void clickBt(View v){
        if(v.getId() == R.id.bt1){
            wv.setVisibility(View.VISIBLE);
            wv.loadUrl("https://www.instagram.com");
            ll.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.bt2){
            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.VISIBLE);
            iv.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.bt3){
            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.VISIBLE);

        }
    }
}
