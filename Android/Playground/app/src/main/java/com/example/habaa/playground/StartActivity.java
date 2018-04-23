package com.example.habaa.playground;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static String serverUrl = "http://70.12.114.137/playground";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        Boolean loginChecker = getResources().getBoolean(Integer.parseInt("loginChecker"));
        //loginChecker = pref.getBoolean();
        if(loginChecker){
            Intent intent=new Intent(StartActivity.this, MainFrameActivity.class);
            startActivity(intent);
        }else {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_start);

            reg = findViewById(R.id.btnGoReg);
            login = findViewById(R.id.btnGoLogin);
        }
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
