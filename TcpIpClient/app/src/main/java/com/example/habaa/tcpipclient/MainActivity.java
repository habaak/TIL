package com.example.habaa.tcpipclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {

    private Client client;
    private EditText etSpeed;
    private Button btnSend;
    private TextView tvShow;
    private String message;
    private Socket socket;
    private String ipAddress = "192.168.1.169";
    //private String ipAddress = "192.168.1.37";
    private String port = "8888";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSpeed = findViewById(R.id.etSpeed);
        btnSend = findViewById(R.id.btnSend);
        tvShow = findViewById(R.id.tvShow);
        client = new Client();
    }
    public void clickBtnSend(View v) throws IOException {
        if(etSpeed.getText() != null && !etSpeed.getText().toString().trim().equals("")){
            message = etSpeed.getText().toString().trim();

            client.execute(new String[] {ipAddress,port,message});

        }
    }

    class Client extends AsyncTask<String, Void, String> {

        boolean connflag;
        boolean flag;
        boolean rflag;
        String ipAddress;
        int port;
        Socket socket;
        InputStream is;
        DataInputStream dis;

        public Client(){
            connflag = true;
            flag = true;
            rflag = true;

        }

        @Override
        protected String doInBackground(String... strings) {
            Socket socket=null;

            OutputStream os;
            DataOutputStream dos;
            //========conn=========
            boolean connCheck = false;
            int connConunt=0;
            for(String str : strings){
                System.out.print(str);
            }
            while (connConunt < 11){
                try {

                    socket = new Socket(strings[0], Integer.parseInt(strings[1]));
                    System.out.println("Connect Connection..." + connConunt);
                    if (socket != null && socket.isConnected()) {
                        connConunt = 11;
                        connCheck = true;
                    }
                    os = socket.getOutputStream();
                    dos = new DataOutputStream(os);
                    dos.writeUTF(strings[2]);
                }catch (IOException e) {
                    connConunt++;
                    System.out.println("Re-Try Connection..." + connConunt);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }


            return strings[2];
        }
    }
}
