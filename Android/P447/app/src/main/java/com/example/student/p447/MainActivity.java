package com.example.student.p447;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ItemAdapter itemAdapter;
    ArrayList<Item> list;
    LinearLayout container;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);

        list = new ArrayList<>();
        list.add(new Item("소녀시대1","010-1111-1111",29,R.drawable.icon4));
        list.add(new Item("소녀시대2","010-2222-2222",30,R.drawable.icon5));
        list.add(new Item("소녀시대3","010-3333-3333",31,R.drawable.icon6));
        list.add(new Item("소녀시대4","010-4444-4444",32,R.drawable.icon7));
        list.add(new Item("소녀시대5","010-5555-5555",33,R.drawable.icon8));
        list.add(new Item("소녀시대6","010-6666-6666",34,R.drawable.icon4));
        list.add(new Item("소녀시대7","010-7777-7777",35,R.drawable.icon10));
        itemAdapter = new ItemAdapter(list);
        listView = findViewById(R.id.listView);
        listView.setAdapter(itemAdapter);
    }
    //ItemAdapter
    public class ItemAdapter extends BaseAdapter {
        ArrayList<Item> list;

        public ItemAdapter(){}

        public ItemAdapter(ArrayList<Item> list){ //Context : 안드로이드 액티비티 정보

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

        public void addItem(Item item){
            list.add(item);
        }

        public void clickbt(View v){
            itemAdapter.addItem(new Item("소녀시대8","010-7777-7777",35,R.drawable.icon10));
            itemAdapter.notifyDataSetChanged();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View vw = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vw = inflater.inflate(R.layout.item, container, true);
            TextView name = vw.findViewById(R.id.textView);
            TextView phone = vw.findViewById(R.id.textView2);
            TextView age = vw.findViewById(R.id.textView3);
            ImageView img = vw.findViewById(R.id.imageView);

            name.setText(list.get(i).getName());
            phone.setText(list.get(i).getMobile());
            age.setText(list.get(i).getAge()+"");
            img.setImageResource(list.get(i).getResId());

            return vw;
        }
    }
}
