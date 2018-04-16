package com.example.student.p536;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }
    public void clickBt(View v){
        t.start();
    }

    Thread t = new Thread(new Runnable() {
        int i = 1;
        @Override
        public void run() {
            while(i<=10){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
                runOnUiThread(new Runnable() { //thread안에 runOnUiThread를 만들어서 ui에 접근할 수 있도록 해야한다.
                    @Override
                    public void run() {
                        textView.setText(i+"");
                    }
                });
                //textView.setText(i+"");//sub thread에서는 main thread의 ui를 건들 수 없다
            }
        }
    });
}
