package com.example.student.p467;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    SingerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new SingerAdapter();

        adapter
    }
    public class SingerAdapter extends BaseAdapter {
        ArrayList<Singer> list;

        public SingerAdapter(){}

        public SingerAdapter(ArrayList<Singer> list) {
            this.list = list;
        }
    }
}
