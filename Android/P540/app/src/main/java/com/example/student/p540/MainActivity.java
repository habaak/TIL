package com.example.student.p540;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    MyHandler myHandler;
    MyHandler myHandler2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        myHandler = new MyHandler();
        myHandler2 = new MyHandler();
    }
    public void clickBt(View v){
        t.start();
    }

    Thread t = new Thread(new Runnable() {
        int i = 0;
        @Override
        public void run() {
            while (1<=10){
                i++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();//큰 상자 역할
                Bundle bundle = msg.getData();//작은 상자 역할
                bundle.putInt("data",i);
                myHandler.sendMessage(msg);
            }
        }
    });

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            /*super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int res = bundle.getInt("data");
            if (res == 11){
                textView.setText("Finish");
            }else{
                textView.setText(bundle.getInt("data")+"");
            }*/
            Message msg2 = new Message();
            Bundle bundle = msg2.getData();
            bundle.putInt("data2",11);
            myHandler2.sendMessage(msg2);
        }
    }

    class MyHandler2 extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int res = bundle.getInt("data2");
            if (res == 11){
                textView.setText("Finish");
            }else{
                textView.setText(bundle.getInt("data2")+"");
            }
        }
    }
}
