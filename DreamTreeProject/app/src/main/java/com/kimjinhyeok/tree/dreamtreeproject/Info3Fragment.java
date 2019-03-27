package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Info3Fragment extends Fragment {
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
        data.add("도시락류, 김밥류, 샌드위치, 과일류, 햄류, 우유 및 두유류, 덮밥, 볶음밥류, 반찬류, 요구르트, 쥬스류");
        adapter = new ItemAdapter(context, data, R.layout.infoitem);
        listView.setAdapter(adapter);
        return view;
    }

}