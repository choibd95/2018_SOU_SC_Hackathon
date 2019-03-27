package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class BlankFragment extends Fragment {

    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }



    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            doAction1();
        }
    };

    void doAction1(){

        Toast.makeText(getActivity(), "토스트", Toast.LENGTH_SHORT).show();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);


        view.findViewById(R.id.button).setOnClickListener(handler);

        return view;
    }

}


