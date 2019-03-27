package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapPoint;

import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.Midx;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.Midxs;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.mainflag;

public class AreaFragment extends Fragment {
   static Context context;
    View view;
    static AreaAdaptor AreaAdaptor;
    static ListView listView;
    static String area;
    private http    testHttp    =null;
    View bg1;
    TextView text;
    public static void click()
    {

        http.getInstance().urlp = 3;
        Log.v("http","http");
        if(http.getInstance().arealist != null)
        {
            NameSeach.flag = false;

            AreaAdaptor = new AreaAdaptor(http.getInstance().arealist, context, R.layout.areaitem, 0);
            listView.setAdapter(AreaAdaptor);
            AreaAdaptor.notifyDataSetChanged();
        }
    else
        {

            Toast.makeText(context, "다시 시도해주세요", Toast.LENGTH_SHORT).show();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_area, container, false);



        text = (TextView) view.findViewById(R.id.addtext);


        listView = (ListView)view.findViewById(R.id.search_listView);


        AreaAdaptor = new AreaAdaptor(http.getInstance().arealist, context,R.layout.areaitem, 0);

        listView.setAdapter(AreaAdaptor);

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
                    DialogRadio();
                    break;
            }
        }
    };

    private void DialogRadio(){
        final CharSequence[] address = {"강남구", "강동구", "강북구" , "강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구",
                "동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구", "중구","중랑구"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(getContext());
        alt_bld.setIcon(R.drawable.map2);
        alt_bld.setTitle("지역 선택");

        alt_bld.setSingleChoiceItems(address, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                text.setText(address[item].toString());
                area = address[item].toString();
                http.getInstance().urlp = 3;
                http.getInstance().doAction2();
                dialog.cancel();
                Toast.makeText(context, area+" 가맹점 검색", Toast.LENGTH_SHORT).show();


            }




        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }


    ListView.OnItemClickListener itemhandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(http.getInstance().arealist != null) {


                TMapPoint point = new TMapPoint(http.getInstance().arealist.get(position).getLatitude(), http.getInstance().arealist.get(position).getLongitude());
                viewFragment.name = http.getInstance().arealist.get(position).getName();
                viewFragment.tel = http.getInstance().arealist.get(position).getTEL();
                viewFragment.gu = http.getInstance().arealist.get(position).getGu();
                viewFragment.dong = http.getInstance().arealist.get(position).getDong();
                viewFragment.address = http.getInstance().arealist.get(position).getAddress();
                viewFragment.kategorie = http.getInstance().arealist.get(position).getKategorie();
                viewFragment.Midx = http.getInstance().arealist.get(position).getMidx();
                Midxs = String.valueOf(Midx);
                viewFragment.distance3 = "거리 알수없음";
                viewFragment.polypoint = point;
                mainflag = true;
                Intent intent = new Intent(context, DetailActivity.class);
                startActivity(intent);

            }else
            {
                Toast.makeText(context, "인터넷을 켜주세요", Toast.LENGTH_SHORT).show();
            }
        }
    };




}
