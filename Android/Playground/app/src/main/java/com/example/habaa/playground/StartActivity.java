package com.example.habaa.playground;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private WebView startView;
    private Handler handler = new Handler();
    Button reg,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        reg = findViewById(R.id.btnGoReg);
        login = findViewById(R.id.btnGoLogin);
    }
    public void clickBt(View v){
        if(v.getId()==R.id.btnGoReg){
            Intent intent=new Intent(StartActivity.this, RegisterActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnGoLogin){
            Intent intent2=new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent2);
        }
    }
}
