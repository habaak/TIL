package com.example.student.p553;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MyService extends Service {
    boolean flag = false;
    String cmd;
    public MyService() {
    }
    public int onStartCommand(Intent intent, int flags, int startId) { //intent로부터 명령을 받아 실행됨
        Log.d(TAG, "Service onStartCommand....");
        if (intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);

    }

    private void processCommand(Intent intent) {
        /*cmd = intent.getStringExtra("cmd");
        if(cmd=="stop"){
            flag= true;
        }*/

        String command = intent.getStringExtra("cmd");
        Log.d(TAG, "Service onStartCommand...." + command);
        final Intent sintent = new Intent(getApplicationContext(), MainActivity.class);
        sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable run = new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i < 7; i++) {
                    Log.d(TAG, "Process"+i);
                    sintent.putExtra("data",i);
                    startActivity(sintent);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        new Thread(run).start();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
