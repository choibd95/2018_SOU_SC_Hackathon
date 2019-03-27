package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Info4Fragment extends Fragment {
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
        data.add("대상자 본인인 청소년, 아동만 사용하기 바랍니다. (어른 및 타인 사용 불가능 - 향후 발생시에 급식에서 제외)\n" +
                "전출 또는 급식대상자에서 제외 되었을 경우 꿈나무카드를 동주민센터로 반납하여 주시기 바랍니다.\n" +
                "분실· 훼손·도난 되었을때는 동 주민센터로 연락주시기 바랍니다.\n" +
                "방학중에 만 지원받는 아동들은 방학종료시 반드시 동 주민센터로 반납하시기 바랍니다.");
        adapter = new ItemAdapter(context, data, R.layout.infoitem);
        listView.setAdapter(adapter);
        return view;
    }

}