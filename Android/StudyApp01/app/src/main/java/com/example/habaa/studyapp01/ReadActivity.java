package com.example.habaa.studyapp01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadActivity extends AppCompatActivity {
    String dirPath = MainActivity.dirPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Intent intent = getIntent();
        String filename = intent.getStringExtra("filename");
        String contents = readTxt(filename);
        TextView textView = (TextView)findViewById(R.id.textview);
        textView.setText(contents);
    }
    public String readTxt(String filename){
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStream is = new FileInputStream(dirPath + "/" + filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }

        } catch (Exception e) {
            Log.i("READ_TXT", e.toString());
        }

        return stringBuffer.toString();
    }

}
