package com.example.habaa.studyapp01;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dirPath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/StudyApp01";
        File file = new File(dirPath);
        if(!file.exists())
            file.mkdirs();


    }
    public void onClickedWrite(View v){
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }
    public void onClickedList(View v){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    public void onClickedExit(){
        finish();
    }
}
