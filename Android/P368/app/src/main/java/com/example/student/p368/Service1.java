package com.example.student.p368;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

public class Service1 extends Service {
    public Service1() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"service1 start");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"service1 onStartCommand");
        if(intent == null){
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("service1");
        int cnt = intent.getIntExtra("cnt",0);
        Log.d(TAG, "Service onStartCommand..."+command+cnt);
        final Intent sintent = new Intent(getApplicationContext(), MainActivity.class);
        sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "Process"+i);
                    sintent.putExtra("command","service1");
                    sintent.putExtra("cnt",i);
                    startActivity(sintent);
                    try {
                        Thread.sleep(1000);
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
