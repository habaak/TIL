package com.example.student.p230;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button home, register, login, chart,naver;
    WebView naverView;
    RelativeLayout loginView, registerView;
    LinearLayout chartView, homeView;
    private TextView timeTv;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeui();
        timeTv = (TextView) findViewById(R.id.timeTv);

        MainTimerTask timerTask = new MainTimerTask();
        mTimer = new Timer();
        mTimer.schedule(timerTask, 500, 1000);


    }
    public void makeui(){

        home = findViewById(R.id.home);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        chart = findViewById(R.id.chart);
        naver = findViewById(R.id.naver);
        homeView = findViewById(R.id.homeView);
        loginView = findViewById(R.id.loginView);
        registerView = findViewById(R.id.registerView);
        chartView = findViewById(R.id.chartView);
        naverView = findViewById(R.id.naverView);

        naverView.setWebViewClient(new WebViewClient());
        naverView.getSettings().setJavaScriptEnabled(true);
        loginView.setVisibility(View.INVISIBLE);
        registerView.setVisibility(View.INVISIBLE);
        chartView.setVisibility(View.INVISIBLE);
        naverView.setVisibility(View.INVISIBLE);
    }
    public void clickBt(View v){
        if(v.getId()==R.id.home){
            homeView.setVisibility(View.VISIBLE);
            loginView.setVisibility(View.INVISIBLE);
            registerView.setVisibility(View.INVISIBLE);
            chartView.setVisibility(View.INVISIBLE);
            naverView.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.register){
            homeView.setVisibility(View.INVISIBLE);
            loginView.setVisibility(View.INVISIBLE);
            registerView.setVisibility(View.VISIBLE);
            chartView.setVisibility(View.INVISIBLE);
            naverView.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.login){
            homeView.setVisibility(View.INVISIBLE);
            loginView.setVisibility(View.VISIBLE);
            registerView.setVisibility(View.INVISIBLE);
            chartView.setVisibility(View.INVISIBLE);
            naverView.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.chart){
            homeView.setVisibility(View.INVISIBLE);
            loginView.setVisibility(View.INVISIBLE);
            registerView.setVisibility(View.INVISIBLE);
            chartView.setVisibility(View.VISIBLE);
            naverView.setVisibility(View.INVISIBLE);
        }else if(v.getId()==R.id.naver){
            homeView.setVisibility(View.INVISIBLE);
            loginView.setVisibility(View.INVISIBLE);
            registerView.setVisibility(View.INVISIBLE);
            chartView.setVisibility(View.INVISIBLE);
            naverView.setVisibility(View.VISIBLE);
            naverView.loadUrl("https://m.naver.com");
        }
    }

    private Handler mHandler = new Handler();

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {

            Date rightNow = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy.MM.dd hh:mm:ss ");
            String dateString = formatter.format(rightNow);
            timeTv.setText(dateString);

        }
    };

    class MainTimerTask extends TimerTask {
        public void run() {
            mHandler.post(mUpdateTimeTask);
        }
    }
    @Override
    protected void onDestroy() {
        mTimer.cancel();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mTimer.cancel();
        super.onPause();
    }

    @Override
    protected void onResume() {
        MainTimerTask timerTask = new MainTimerTask();
        mTimer.schedule(timerTask, 500, 3000);
        super.onResume();
    }

}
