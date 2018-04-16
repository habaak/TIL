package com.example.student.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class PicAdapter  extends BaseAdapter {
    ArrayList<Pic> list;
    Context context;

    public PicAdapter(ArrayList<Pic> list){}

    public PicAdapter(Context context, ArrayList<Pic> list){ //Context : 안드로이드 액티비티 정보
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(Pic pic){
        list.add(pic);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vw = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //vw = inflater.inflate(R.layout.item, R.id.listView, true);
        return vw;
    }
}
