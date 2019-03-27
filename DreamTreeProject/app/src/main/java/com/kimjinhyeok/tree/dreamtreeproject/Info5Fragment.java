package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Info5Fragment extends Fragment {
    Context context;
    View view;

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

    ArrayList<String> data = new ArrayList<>();
    ListView listView;
    private ItemAdapter adapter = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_info, container, false);

        listView = (ListView)view.findViewById(R.id.listView);
        data.add("어플 문의 : nox6438@naver.com \n \n 서울시 꿈나무 카드문의 : 각 관할 구청이나 동사무소로 문의바랍니다.");
        adapter = new ItemAdapter(context, data, R.layout.infoitem);
        listView.setAdapter(adapter);
        return view;
    }

}