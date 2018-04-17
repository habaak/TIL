package com.example.student.p553;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView, imageView2,imageView3;
    MyHandler myHandler;

    int imgs [] = {
        R.drawable.icon4,R.drawable.icon5,R.drawable.icon6,R.drawable.icon7,R.drawable.icon8,R.drawable.icon9,R.drawable.icon10
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        myHandler = new MyHandler();
    }
    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            for (int i =1;i<=7;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int cnt = i;
                runOnUiThread(new Runnable() { //thread안에 runOnUiThread를 만들어서 ui에 접근할 수 있도록 해야한다.
                    @Override
                    public void run() {
                        /*if(i%2==1){
                            imageView.setImageResource(R.drawable.icon10);
                        }else {
                            imageView.setImageResource(R.drawable.icon4);
                        }*/
                        imageView.setImageResource(imgs[cnt -1]);
                    }
                });
            }
        }
    };

    Thread t = new Thread(new Runnable() {

        @Override
        public void run() {
            for (int i =1;i<=7;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int cnt = i;
                runOnUiThread(new Runnable() { //thread안에 runOnUiThread를 만들어서 ui에 접근할 수 있도록 해야한다.
                    @Override
                    public void run() {
                        /*if(i%2==1){
                            imageView.setImageResource(R.drawable.icon10);
                        }else {
                            imageView.setImageResource(R.drawable.icon4);
                        }*/
                        imageView.setImageResource(imgs[cnt -1]);
                    }
                });
            }
        }
    });
    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            for (int i =1;i<=7;i++){
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
    };
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i =1;i<=7 ;i++){
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

    class  MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int res = bundle.getInt("data");
            if (res%2==1){
                imageView2.setImageResource(R.drawable.icon7);
            }else {
                imageView2.setImageResource(R.drawable.icon8);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent!=null){
            int num = 0;
            int data = intent.getIntExtra("data",num);
            if (data%2==1){
                imageView3.setImageResource(R.drawable.icon4);
            }else {
                imageView3.setImageResource(R.drawable.icon5);
            }
        }
    }

    public void clickBt(View v){
        Thread t = new Thread(r1);
        Thread t2 = new Thread(r2);
        t.start();
        t2.start();

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("cmd","start");
        startService(intent);
    }
    public void clickBt2(View V){
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("cmd","stop");
        startService(intent);
    }
}
