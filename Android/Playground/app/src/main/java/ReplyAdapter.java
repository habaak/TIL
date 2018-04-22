import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.habaa.playground.R;
import com.example.habaa.playground.reply;

import java.util.ArrayList;

/**
 * Created by habaa on 2018-04-22.
 */

public class ReplyAdapter extends BaseAdapter{
    Context context;
    ArrayList<reply> list_itemArrayList;

    public ReplyAdapter(){}
    public ReplyAdapter(Context context, ArrayList<reply> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {

        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return list_itemArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.reply,null);

        }
        return view;
    }
}
