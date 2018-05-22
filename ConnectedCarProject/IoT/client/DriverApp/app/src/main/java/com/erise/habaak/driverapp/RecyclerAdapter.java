package com.erise.habaak.driverapp;
/**
 * Created by habaa on 2018-05-18.
 */


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import 	android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erise.habaak.driverapp.MainActivity;
import com.erise.habaak.driverapp.R;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<MainActivity.Recycler_item> items;
    int item_layout;
    public RecyclerAdapter(Context context, List<MainActivity.Recycler_item> items, int item_layout){
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,null);
        return new RecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        final MainActivity.Recycler_item item = items.get(position);
        Drawable drawable = context.getResources().getDrawable(item.getImage());
        holder.image.setBackground(drawable);
        holder.title.setText(item.getTitle());
        holder.cardview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}