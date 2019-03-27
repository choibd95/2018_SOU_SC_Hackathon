package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.skt.Tmap.TMapPoint;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.Midx;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.Midxs;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.mainflag;

public class ThirdFragment extends Fragment {
    static Context context;
    View view;
    static AddressAdaptor AddressAdaptor;
    static ListView listView;
    View bg1;
    double distance;
    double distance2;
    String distance3;


    ListView.OnItemClickListener itemhandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(http.getInstance().list != null) {
                TMapPoint point = new TMapPoint(http.getInstance().list.get(position).getLatitude(), http.getInstance().list.get(position).getLongitude());

                distance = http.getInstance().list.get(position).getDistance();
                ;
                distance2 = Double.parseDouble(String.format("%.1f", distance));
                distance3 = Double.toString(distance2);
                viewFragment.tel = http.getInstance().list.get(position).getTEL();
                viewFragment.gu = http.getInstance().list.get(position).getGu();
                viewFragment.dong = http.getInstance().list.get(position).getDong();
                viewFragment.address = http.getInstance().list.get(position).getAddress();
                viewFragment.kategorie = http.getInstance().list.get(position).getKategorie();
                viewFragment.distance3 = distance3;
                viewFragment.polypoint = point;
                viewFragment.name = http.getInstance().list.get(position).getName();
                Midx = http.getInstance().list.get(position).getMidx();
                Midxs = String.valueOf(Midx);




                mainflag = true;
                Intent intent = new Intent(context, DetailActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(context, "인터넷을 켜주세요", Toast.LENGTH_SHORT).show();
            }
            }
    };

    public static void click()
    {

        if(http.getInstance().list != null)
        {
            AddressAdaptor = new AddressAdaptor(http.getInstance().list, context, R.layout.arounditem, 0);
            listView.setAdapter(AddressAdaptor);
            AddressAdaptor.notifyDataSetChanged();
            Toast.makeText(context, "주변검색 완료", Toast.LENGTH_SHORT).show();
        }



    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public void onPause() {
        super.onPause();
        if(NameSeach.editText != null)
        {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(NameSeach.editText, 0);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        http.getInstance().urlp = 20;
        if(http.getInstance().lon != null)
        {
            http.getInstance().doAction2();
        }
       view = inflater.inflate(R.layout.fragment_third, container, false);

        listView = (ListView)view.findViewById(R.id.search_listView);

        AddressAdaptor = new AddressAdaptor(http.getInstance().list, context,R.layout.arounditem, 0);

        listView.setAdapter(AddressAdaptor);

        listView.setOnItemClickListener(itemhandler);

        bg1 = view.findViewById(R.id.bg11);
        bg1.setOnClickListener(handler);



        return view;
    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.bg11:
                    viewon();
                    break;
            }
        }
    };



    public void viewon()
    {

        if(http.getInstance().lon != null)
        {
            http.getInstance().urlp = 1;
            http.getInstance().searchflag = true;
            http.getInstance().doAction2();
        }
        else
        {
            Toast.makeText(context, "현재위치를 찾고있습니다 잠시만 기다려주세요", Toast.LENGTH_SHORT).show();
        }
    }


}
