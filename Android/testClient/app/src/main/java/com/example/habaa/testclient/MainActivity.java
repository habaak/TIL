package com.example.habaa.testclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private Client client;
    private EditText etSpeed;
    private Button btnSend;
    private String message;
    private ImageView ivImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSpeed = findViewById(R.id.etSpeed);
        btnSend = findViewById(R.id.btnSend);
        ivImg = findViewById(R.id.ivImg);

        try {
            client = new Client();

        } catch (IOException e) {
            e.printStackTrace();
        }
        client.start();
    }

    public void onClickBtn(View v) {
        String msg = etSpeed.getText().toString();
        client.sendMsg(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.stopClient();
    }

    public void convertImg(final String str){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(str.equals("1")){
                            ivImg.setImageResource(R.drawable.tester);
                        } else if(str.equals("2")){
                            ivImg.setImageResource(R.drawable.tester2);
                        }else  if(str.equals("3")){
                            ivImg.setImageResource(R.drawable.tester3);
                        }
                    }
                });
            }
        };
        new Thread(r).start();
    }


    //Client Socket Start

    public class Client extends Thread {

        boolean flag = true;
        boolean cflag = true;
        /* String address = "192.168.0.39"; */
        // String address = "203.246.196.46";
        String address = "192.168.0.55";
        Socket socket;

        public Client() throws UnknownHostException, IOException {

        }

        @Override
        public void run() {
            while (cflag) { // 서버와 통신 될 때 까지 접속 시도 루프
                try {
                    Log.d("[Client App]","Try Connecting Server...");

                    socket = new Socket(address, 8888);
                    //socket.setSoTimeout(5000);
                    Log.d("[Client App]","Connected Server...");
                    if (socket != null && socket.isConnected()) {
                        cflag = false;
                    }
                    break;
                } catch (IOException e) {
                    Log.d("[Client App]","Re-Try Connection...");
                    //Toast.makeText(MainActivity.this,"Retry",Toast.LENGTH_LONG).show();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            // After Connected ...
            try {
                new Receiver(socket).start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void sendMsg(String msg) {
            try {
                Sender sender = new Sender(socket);
                sender.setSendMsg(msg);
                Log.d("[Server App]",sender.toString());
                new Thread(sender).start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // 소켓이 만들어지

        class Sender implements Runnable { // 문자를 입력하면 Sender가 만들어지고 전송

            Socket socket;
            OutputStream os;
            DataOutputStream dos;
            String sendMsg;

            public Sender(Socket socket) throws IOException {
                this.socket = socket;
                os = socket.getOutputStream();
                dos = new DataOutputStream(os);
            }

            public void setSendMsg(String sendMsg) {
                this.sendMsg = sendMsg;
            }

            @Override
            public void run() {
                try {
                    if (dos != null) {
                        dos.writeUTF(sendMsg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        class Receiver extends Thread { // 들어올때까지 기다리고 읽는다.
            Socket socket;
            InputStream is;
            DataInputStream dis;

            public Receiver() {
            }

            public Receiver(Socket socket) throws IOException {
                this.socket = socket;
                is = socket.getInputStream();
                dis = new DataInputStream(is);
            }

            @Override
            public void run() {
                try {
                    while (flag && dis != null) {

                        String str = dis.readUTF();
                        Log.d("[Client App]",str);
                        convertImg(str);
                    }
                } catch (Exception e) {
                    Log.d("[Client App]","Server Closed");
                } finally { //while이 끝나는 시점
                    try {
                        dis.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                stopClient();
            }
        }

        public void stopClient() {
            try {
                Thread.sleep(1000);
                flag = false;
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    //Client Socket End

}
