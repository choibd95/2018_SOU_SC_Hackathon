package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Neal on 2016. 8. 27..
 */
public class reviewAdaptor extends BaseAdapter {

    private ArrayList<reviewvo> list;
    private Context context;
    private int layout;
    private int switching;

    public reviewAdaptor(ArrayList<reviewvo> list, Context context, int layout, int switching) {
        this.list = list;
        this.context = context;
        this.layout = layout;
        this.switching = switching;
    }

    @Override
    public int getCount() {
        if (list == null) {
//            Toast.makeText(viewFragment.context, "다시 시도해주세요", Toast.LENGTH_SHORT).show();
        } else {
            return list.size();
        }

        return 0;

    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class forItem {

        private TextView nick;
        private TextView text;

    }


    forItem forItem = null;

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {

        if (currentView == null) {
            currentView = View.inflate(context, layout, null);

            forItem = new forItem();

//            forItem.name = (TextView) currentView.findViewById(R.id.name);
//            forItem.tel = (TextView) currentView.findViewById(R.id.tel);
//            forItem.Address = (TextView) currentView.findViewById(R.id.Address);
//            forItem.Kategorie = (TextView) currentView.findViewById(R.id.Kategorie);
//            forItem.distance = (TextView) currentView.findViewById(R.id.distance);
//            forItem.imageView = (ImageView) currentView.findViewById(R.id.imageView4);

            forItem.nick = (TextView) currentView.findViewById(R.id.name);
            forItem.text = (TextView) currentView.findViewById(R.id.editText3);

            currentView.setTag(forItem);
        } else {
            forItem = (forItem) currentView.getTag();
        }

        final reviewvo reviewvo = list.get(position);



        forItem.nick.setText(reviewvo.getNickname());

        forItem.text.setText(reviewvo.getText());



        return currentView;

    }
}
