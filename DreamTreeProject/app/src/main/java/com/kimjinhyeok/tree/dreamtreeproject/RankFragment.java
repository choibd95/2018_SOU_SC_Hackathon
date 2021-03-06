package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.skt.Tmap.TMapPoint;

import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.Midxs;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.mainflag;

public class RankFragment extends Fragment {
    static Context context;
    View view;
    static RankAdaptor RankAdaptor;
    static ListView listView;
    View bg1;
    double distance;
    double distance2;
    String distance3;


    ListView.OnItemClickListener itemhandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(http.getInstance().ranklist != null) {
                TMapPoint point = new TMapPoint(http.getInstance().ranklist.get(position).getLatitude(), http.getInstance().ranklist.get(position).getLongitude());

                distance = http.getInstance().ranklist.get(position).getDistance();
                ;
                distance2 = Double.parseDouble(String.format("%.1f", distance));
                distance3 = Double.toString(distance2);
                viewFragment.tel = http.getInstance().ranklist.get(position).getTEL();
                viewFragment.gu = http.getInstance().ranklist.get(position).getGu();
                viewFragment.dong = http.getInstance().ranklist.get(position).getDong();
                viewFragment.address = http.getInstance().ranklist.get(position).getAddress();
                viewFragment.kategorie = http.getInstance().ranklist.get(position).getKategorie();
                viewFragment.distance3 = distance3;
                viewFragment.polypoint = point;
                viewFragment.name = http.getInstance().ranklist.get(position).getName();

                int idxa  = http.getInstance().ranklist.get(position).getMidx();
                String idxb = String.valueOf(idxa);

                Midxs = idxb;
                Log.v("idx",idxb);


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

        if(http.getInstance().ranklist != null)
        {
            RankAdaptor = new RankAdaptor(http.getInstance().ranklist, context, R.layout.rankitem, 0);
            listView.setAdapter(RankAdaptor);
            RankAdaptor.notifyDataSetChanged();
            Toast.makeText(context, "조회 완료", Toast.LENGTH_SHORT).show();
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

        http.getInstance().urlp = 8;
        http.getInstance().doAction2();

       view = inflater.inflate(R.layout.fragment_rank, container, false);

        listView = (ListView)view.findViewById(R.id.search_listView);

        RankAdaptor = new RankAdaptor(http.getInstance().ranklist, context,R.layout.arounditem, 0);

        listView.setAdapter(RankAdaptor);

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
            http.getInstance().urlp = 8;
            http.getInstance().doAction2();
    }


}
