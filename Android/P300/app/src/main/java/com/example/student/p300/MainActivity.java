package com.example.student.p300;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Intent intent;
    ProgressBar progressBar;
    ImageView iv;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setMax(90);
        iv = findViewById(R.id.imageView);
        progressDialog = new ProgressDialog(MainActivity.this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        ProcessIntent(intent);
    }

    private void ProcessIntent(Intent intent) {
        if(intent != null){
            String command = intent.getStringExtra("command");
            final int cnt = intent.getIntExtra("cnt",0);
            textView.setText(""+command+" "+cnt);
            progressBar.setProgress(
                    cnt*10
            );
            if(cnt%2==0){
                iv.setImageResource(R.drawable.bg13);
            }else{
                iv.setImageResource(R.drawable.bg3);
            }

        }
    }

    public void clickBt(View v){
        String name = editText.getText().toString();
        intent = new Intent(this,MyService.class);
        intent.putExtra("command","start");
        intent.putExtra("name", name);
        startService(intent);
    }
    public void clickBt2(View v) {
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.setMessage("진행중");
        progressDialog.show();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressDialog.dismiss();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent != null){
            stopService(intent);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builer = new AlertDialog.Builder(this);
        builer.setTitle("경고");
        builer.setMessage("끝내시겠습니까?");
        builer.setIcon(R.drawable.images);
        builer.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builer.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builer.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builer.create().show();
    }
}