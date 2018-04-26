package com.example.habaa.playground;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.habaa.playground.Contents;
import com.example.habaa.playground.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016-10-13.
 */
public class ContentsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private List<Contents> list;

    public ContentsAdapter(Context context, List<Contents> list){
        // MainActivity 에서 데이터 리스트를 넘겨 받는다.
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Contents getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    static class ViewHolder {
        public ImageView ivPic;
        public TextView tvComment;
    }
    public List<Contents> getList() {
        return list;
    }

    public void setList(List<Contents> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.contents,viewGroup,false);

            viewHolder = new ViewHolder();
            viewHolder.ivPic= (ImageView) convertView.findViewById(R.id.ivPic);
            viewHolder.tvComment = (TextView) convertView.findViewById(R.id.tvComment);

            //viewHolder.label = convertView.findViewById(R.id.label);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Contents contents = getItem(position);

        Log.d("CONTEXT?????????", String.valueOf(context));
        Picasso
                .with(getContext())
                .load(contents.getImgUrl())
                //.fit() // will explain later
                .into(viewHolder.ivPic);
        viewHolder.tvComment.setText(contents.getCmt());


        // 각 셀에 넘겨받은 텍스트 데이터를 넣는다.
        //viewHolder.label.setText((CharSequence) list.get(position));
        return convertView;
    }

    /*class ViewHolder{
        public LinearLayout label;
    }*/
}