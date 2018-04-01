package com.example.habaa.studyapp01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
   private List<String> item = null;
   private List<String> path = null;

   private String dirPath = MainActivity.dirPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        getDir(dirPath);
    }
    private void getDir(String dirPath) {
        item = new ArrayList<String>();
        path = new ArrayList<String>();
        File f = new File(dirPath);//경로 저장
        File[] files = f.listFiles();//파일 리스트 저장
        for (File file : files) {
            item.add(file.getName());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, item);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String filename = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListActivity.this, ReadActivity.class);
                intent.putExtra("filename", filename);
                startActivity(intent);
                Log.i("FILE_NAME : ", filename);
            }
        });
    }
}
