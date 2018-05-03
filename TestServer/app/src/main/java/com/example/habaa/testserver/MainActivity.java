package com.example.habaa.testserver;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Server server;
    private TextView txtV1;
    private TextView txtV2;
    private EditText etText;
    private Button btnSend;
    private boolean rflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtV1 = findViewById(R.id.tvSpeed);
        txtV2 = findViewById(R.id.tvIp);
        etText = findViewById(R.id.etText);
        btnSend = findViewById(R.id.btnSend);

        try {
            server = new Server();

        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
    }
    public void clickBtnSend(View v){
        String str = etText.getText().toString();
        server.sendAll(str);
    }
    public void setSpeed(final String msg){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtV1.setText(msg);
                    }
                });
            }
        };
        new Thread(r).start();
    }

    public void setConnect(final String client, final String msg){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(msg.equals("t")){
                            txtV2.setText("["+client+ "]" +"Client CONNECTED");
                        }else{
                            txtV2.setText("Client DISCONNECTED");
                        }
                    }
                });
            }
        };
        new Thread(r).start();
    }


    //ServerSocket Start....
    public class Server extends Thread{

        ServerSocket serverSocket;
        boolean flag = true;
        boolean rflag = true;
        ArrayList<DataOutputStream> list = new ArrayList<>();

        public Server() throws IOException {
            serverSocket = new ServerSocket(8888);
            Log.d("[Server App]","Server Ready...");
        }

        //start server
        @Override
        public void run() {
            //Accept Client Connection...
            try {
                while(flag) {
                    Log.d("[Server App]","Ready Accept");
                    Socket socket = serverSocket.accept();
                    String client = socket.getInetAddress().getHostAddress();
                    setConnect(client, "t");
                    new Receiver(socket).start();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        class Receiver extends Thread{
            InputStream is;
            DataInputStream dis;
            OutputStream os;
            DataOutputStream dos;
            Socket socket;// Thread가 끝날때 socket을 죽이기 위해

            public Receiver (Socket socket) {
                try {
                    this.socket = socket;
                    is = socket.getInputStream();
                    dis = new DataInputStream(is);
                    os = socket.getOutputStream();
                    dos = new DataOutputStream(os);
                    list.add(dos);
                    Log.d("[Server App]","Connected Count : "+list.size());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



            @Override
            public void run() {
                try {
                    //client가 보내는 메시지를 받는다.
                    while(rflag) {
                        if(socket.isConnected() && dis != null & dis.available() > 0) {
                            String str = dis.readUTF();
                            setSpeed(str);
                            SendHttp sendHttp = new SendHttp(str);
                            sendHttp.execute();
                        }
                    }

                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    setConnect(null,"f");
                    list.remove(dos);
                    //Log.d("[Server App]",list.size());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    if(dis != null) {
                        try {
                            dis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if(dos != null) {
                        try {
                            dos.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if(socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        public void sendAll(String msg) {
            Log.d("[Server App]",msg);
            Sender sender = new Sender();
            sender.setMsg(msg);
            sender.start();
        }

        //Send Message All Clients
        class Sender extends Thread{
            String msg;
            public void setMsg(String msg) {
                this.msg = msg;
            }

            @Override
            public void run() {
                try {
                    if(!list.isEmpty() && list.size()>=0) {
                        for(DataOutputStream dos : list) {
                            dos.writeUTF(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void stopServer() {
            rflag = false;

        }

    }
    //ServerSocket Stop....


    class SendHttp extends AsyncTask<Void,Void,Void>{

        String surl = "http://70.12.114.132:8070/webserver/main.do?speed=";
        URL url;
        HttpURLConnection urlConn;
        String speed;

        public SendHttp(){}
        public SendHttp(String speed){
            this.speed = speed;
            surl +=speed;
            try {
                url = new URL(surl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}