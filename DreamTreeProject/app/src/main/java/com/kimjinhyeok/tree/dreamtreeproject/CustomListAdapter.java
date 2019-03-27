package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by aqoong on 2017. 10. 18..
 */

public class CustomListAdapter extends BaseAdapter {
    private String TAG = "LOG : CustomListAdapter";

    TextView tvmoney;


    //어댑터가 가지고 있는 아이템 리스트
    public ArrayList<ListItem> items = null;

    public CustomListAdapter(){
        items = new ArrayList<>();
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount()");
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d(TAG, "getItem(int)");
        return items.get(position);}

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId(int)");
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView(int, View, ViewGroup)");


        final Context mContext = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        ImageView img = (ImageView)convertView.findViewById(R.id.item_img);

        TextView tvcon = (TextView) convertView.findViewById(R.id.con);
        TextView tvtime = (TextView) convertView.findViewById(R.id.contime);
        tvmoney = (TextView) convertView.findViewById(R.id.money);




        // TextView nametext = (TextView)convertView.findViewById(R.id.text_name);
        // TextView addresstext = (TextView)convertView.findViewById(R.id.text_address);

        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.item_check);


        final ListItem item = items.get(position);


        String money = toNumFormat(item.getMoney());

       //  String money = Integer.toString(item.getMoney());

        tvcon.setText(item.getCon());
        tvtime.setText(item.getIntime());
        tvmoney.setText(money+"원");

        img.setImageResource(item.getImgId());
      //  nametext.setText("이름 :   "+item.getName());
        // addresstext.setText("주소 :   "+item.getAddress());


        checkBox.setChecked(item.isChecked());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setChecked(isChecked);
            }
        });



        if(item.getDiv().equals("수입"))
        {

            String strColor = "#ff0099cc";
            tvmoney.setTextColor(Color.parseColor(strColor));
        }else
            {
                String strColor = "#ffcc0000";
                tvmoney.setTextColor(Color.parseColor(strColor));

            }




        return convertView;
    }

    //아이템 하나씩 추가
    public void addItem(int imgID, boolean isCheck, bankvo vo) {
        ListItem item = new ListItem();

        item.setImgId(imgID);
        item.setChecked(isCheck);


        item.setCon(vo.getCon());
        item.setIntime(vo.getIntime());
        item.setMoney(vo.getMoney());
        item.setSeq(vo.getSeq());


        item.setDiv(vo.getDiv());


//        item.setName(name);
//        item.setAddress(address);


        items.add(item);
    }

    //체크된 아이템 삭제
    public void removeCheckedItem(){
        int size = items.size()-1;

        for(int i = size ; i >= 0 ; i--){
            if(items.get(i).isChecked())
                items.remove(items.get(i));
        }
    }


    /**
     * 모든 아이템이 체크되어 있다면 true
     * @return
     */
    public boolean isAllChecked(){
        boolean result = true;

        for(int i = 0 ; i < items.size() ; i++){
            if(!items.get(i).isChecked()) {
                result = false;
                return result;
            }

        }
        return result;
    }

    public ArrayList<Integer> getItemList(){
        ArrayList<Integer> seqList = new ArrayList<Integer>();
        for (ListItem ivo : items){
            if(ivo.isChecked()) seqList.add(ivo.getSeq());
        }
        return seqList;
    }
    public  String toNumFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }


}
