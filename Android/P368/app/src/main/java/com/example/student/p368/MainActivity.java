package com.example.student.p368;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    Intent intent,intent2,intent3;
    FrameLayout secondView;
    ProgressBar progressBar;
    ImageView iv;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        secondView = findViewById(R.id.FrameLayout);
        progressBar = findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(MainActivity.this);

        progressBar.setMax(90);
        iv = findViewById(R.id.imageView);

        secondView.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        ProcessIntent(intent);
    }
    private void ProcessIntent(Intent intent) {
        if(intent != null){

            String command = intent.getStringExtra("command");
            int cnt;
            /*if (command.equals("service1")){
                cnt =  intent.getIntExtra("cnt",0);
                if(cnt==4){
                    secondView.setVisibility(View.VISIBLE);
                }
            } else if(command.equals("service2")){
                cnt =  intent.getIntExtra("cnt",0);
                progressBar.setProgress(
                        cnt*10
                );
            } else if(command.equals("service3")){
                cnt =  intent.getIntExtra("cnt",0);
                if(cnt%2==0){
                    iv.setImageResource(R.drawable.icon2);
                }else{
                    iv.setImageResource(R.drawable.icon3);
                }
            }*/

            switch(command){
                case "service1":
                     cnt =  intent.getIntExtra("cnt",0);
                    if(cnt==4){
                        secondView.setVisibility(View.VISIBLE);
                    }
                    break;
                case "service2":
                    cnt =  intent.getIntExtra("cnt",0);
                    progressBar.setProgress(
                            cnt*10
                    );
                    break;
                case "service3":
                    cnt =  intent.getIntExtra("cnt",0);
                    if(cnt%2==0){
                        iv.setImageResource(R.drawable.icon2);
                    }else{
                        iv.setImageResource(R.drawable.icon3);
                    }
                    break;
            }
        }
    }
    public void onClickStart(View v){
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.setMessage("진행중");
        progressDialog.show();

        intent = new Intent(this,Service1.class);
        intent.putExtra("command","service1");
        intent.putExtra("cnt", 0);
        startService(intent);

        progressDialog.dismiss();
    }

    public void onClickSecond(View v) {
        intent2 = new Intent(this,Service2.class);
        intent3 = new Intent(this,Service3.class);
        intent2.putExtra("command","service2");
        intent2.putExtra("cnt", 0);
        intent3.putExtra("command","service3");
        intent3.putExtra("cnt", 0);

        startService(intent2);
        startService(intent3);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent != null){
            stopService(intent);
        }
    }
}
