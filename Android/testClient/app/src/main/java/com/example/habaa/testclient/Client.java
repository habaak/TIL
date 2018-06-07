package com.example.habaa.testclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by habaa on 2018-05-02.
 */

public class Client extends Thread {

    boolean flag;
    String address;
    int port;
    Socket socket;
    Scanner scanner;
    boolean rflag;

    public Client() {
        flag = true;
        rflag = true;
        address = "192.168.1.37";
        port = 8888;

    }

    public boolean connectServer() {
        boolean result = false;
        int count = 0;
        while (count < 11) { // 서버와 통신 될 때 까지 접속 시도 루프
            try {
                socket = new Socket(address, port);
                if (socket != null && socket.isConnected()) {
                    count = 11;
                    result = true;
                }
            } catch (IOException e) {
                count++;
                System.out.println("Re-Try Connection..." + count);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return result;
    }

    public void run() {
        if(connectServer()) {
            System.out.println("Connected " + socket.getInetAddress());
            try {
                Thread receiver = new Thread(new Receiver(socket));
                receiver.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message){
        try {
            Sender sender = new Sender(socket);
            sender.setSendMsg(message);
            Thread send = new Thread(sender);
            send.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Sender implements Runnable {
        Socket socket;
        OutputStream out;
        DataOutputStream outw;
        String sendMsg;

        public Sender(Socket socket) throws IOException {
            this.socket = socket;
            out = socket.getOutputStream();
            outw = new DataOutputStream(out);
        }

        public void setSendMsg(String sendMsg) {
            this.sendMsg = sendMsg;
        }

        @Override
        public void run() {
            try {
                if (outw != null) {
                    outw.writeUTF(sendMsg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    class Receiver extends Thread {
        Socket socket;
        InputStream in;
        DataInputStream inr;

        public Receiver(Socket socket) throws IOException {
            this.socket = socket;
            in = socket.getInputStream();
            inr = new DataInputStream(in);
        }

        @Override
        public void run() {
            while (rflag) {
                try {
                    String str = inr.readUTF();
                    System.out.println(str);
                    if (str.trim().equals("q")) {
                        inr.close();
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected...");
                    break;
                }
            }

            try {
                Thread.sleep(1000);
                flag = false;
                socket.close();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}