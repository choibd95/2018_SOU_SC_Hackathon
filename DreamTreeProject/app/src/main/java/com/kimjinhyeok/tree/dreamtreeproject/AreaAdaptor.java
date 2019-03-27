package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

;

/**
 * Created by Neal on 2016. 8. 27..
 */
public class AreaAdaptor extends BaseAdapter {

    private ArrayList<areavo> list;
    private Context context;
    private int layout;
    private int switching;
    double distance;
    double distance2;
    String distance3;
    String tel;
    String tels;
    ImageView imageView;


    public AreaAdaptor(ArrayList<areavo> list, Context context, int layout, int switching) {
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

        private TextView name;
        private TextView tel;
        private TextView Address;
        private TextView Kategorie;
        private ImageView imageView;
    }


    forItem forItem = null;

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {

        if (currentView == null) {
            currentView = View.inflate(context, layout, null);

            forItem = new forItem();

            forItem.name = (TextView) currentView.findViewById(R.id.name);
            forItem.tel = (TextView) currentView.findViewById(R.id.tel);
//            forItem.Address = (TextView) currentView.findViewById(R.id.Address);
            forItem.Kategorie = (TextView) currentView.findViewById(R.id.Kategorie);
            forItem.imageView = (ImageView) currentView.findViewById(R.id.imageView14);

            currentView.setTag(forItem);
        } else
        {
            forItem = (forItem) currentView.getTag();
        }

        final areavo areavo = list.get(position);
//             전화걸기 기능
//        forItem.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:02-" + vo.getTEL()));
//                context.startActivity(intent);

//            }
//        });

        switch (areavo.getKategorie())
        {
            case "한식" :
                forItem.imageView.setImageResource(R.drawable.k4);
                break;
            case "중식" :
                forItem.imageView.setImageResource(R.drawable.c4);
                break;
            case "분식" :
                forItem.imageView.setImageResource(R.drawable.j4);
                break;
            case "제과" :
                forItem.imageView.setImageResource(R.drawable.b4);
                break;
            default:
                forItem.imageView.setImageResource(R.drawable.i4);
                break;
        }



        forItem.name.setText(areavo.getName());
        if(NameSeach.flag)
        {
            forItem.tel.setText(areavo.getGu()+" "+areavo.getDong());
        }
        else
        {
            forItem.tel.setText(areavo.getDong());
        }




//        forItem.Address.setText(areavo.getAddress());
//        forItem.Kategorie.setText(areavo.getKategorie());




//        if(switching == 1){
//            Resources res = currentView.getResources();
//        res.getDimension(R.dimen.item_barState);
//            forItem.item_title.setTextSize(10);
//            forItem.item_barState.setTextSize(8);
//        }


        return currentView;

    }
}
