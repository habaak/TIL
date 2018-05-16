package com.example.habaa.driverapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TimerTask timerTask;
    private Timer timer;

    private Server server;
    private TextView temp;
    private TextView humi;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//  Bottom Nav
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//  RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Recycler_item> items = new ArrayList<>();
        Recycler_item[] item = new Recycler_item[5];
        item[0]=new Recycler_item(R.drawable.ic_dashboard_black_24dp,"경고");
        item[1]=new Recycler_item(R.drawable.ic_dashboard_black_24dp,"과속");
        item[2]=new Recycler_item(R.drawable.ic_dashboard_black_24dp,"온도 하강 요청");
        item[3]=new Recycler_item(R.drawable.ic_dashboard_black_24dp,"범죄발생");
        item[4]=new Recycler_item(R.drawable.ic_dashboard_black_24dp,"영동대로 10중 추돌 사고");

        for (int i=0; i<5; i++) {
            items.add(item[i]);

            recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_main));
        }
//  TCP IP
        temp = findViewById(R.id.temp);
        humi = findViewById(R.id.humi);


        timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    server = new Server();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer = new Timer();
        timer.schedule(timerTask,0,3000);
    }


    public class Recycler_item {
        int image;
        String title;

        int getImage(){
            return  this.image;
        }
        String getTitle(){
            return this.title;
        }
        Recycler_item(int image,String title){
            this.image=image;
            this.title=title;
        }
    }
//  TCP IP Server
    /*
        온도, 습도, 차량정보
    */
    //ServerSocket Start....
    public void setTemp(final String msg){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        temp.setText(msg);
                    }
                });
            }
        };
        new Thread(r).start();
    }
    public void setHumi(final String msg){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        humi.setText(msg);
                    }
                });
            }
        };
        new Thread(r).start();
    }

    public class Server extends Thread{

        ServerSocket serverSocket;
        boolean flag = true;
        boolean rflag = true;
        ArrayList<DataOutputStream> list = new ArrayList<>();

        public Server() throws IOException {
            serverSocket = new ServerSocket(8888);
            Log.i("[Server]","Server Ready...");
        }

        //start server
        @Override
        public void run() {
            //Accept Client Connection...
            try {
                while(flag) {
                    Log.i("[Server]","Ready Accept");
                    Socket socket = serverSocket.accept();
                    String client = socket.getInetAddress().getHostAddress();
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
                    Log.d("[Server]","Connected Count : "+list.size());
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
                            setTemp(str);
                            /*SendHttp sendHttp = new SendHttp(str);
                            sendHttp.execute();*/
                        }
                    }

                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
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
            Log.i("[Server App]",msg);
            Sender sender = new Sender();
            //sender.setMsg(msg);
            sender.start();
        }

        //Send Message All Clients
        class Sender extends Thread{
            /*String msg;
            public void setMsg(String msg) {
                this.msg = msg;
            }*/

            @Override
            public void run() {
                try {
                    if(!list.isEmpty() && list.size()>=0) {
                        for(DataOutputStream dos : list) {
                            dos.writeUTF("t");
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

//  HTTP Client

}
