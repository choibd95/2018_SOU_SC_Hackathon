package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-09-30.
 */
public class ItemAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> data;
    int layout;
    public  ItemAdapter(Context context, ArrayList<String> data, int layout){
        this.context = context;
        this.data =data;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            view = inflater.inflate(layout,viewGroup, false);
        }

        TextView tv = (TextView)view.findViewById(R.id.textView2);
        tv.setText(data.get(position));
        return view;
    }




}
