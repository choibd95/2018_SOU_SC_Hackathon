package com.kimjinhyeok.tree.dreamtreeproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

;

public class Address extends AppCompatActivity {

    ArrayList list;
    AddressAdaptor AddressAdaptor;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

       list  = http.getInstance().list;

        listView = (ListView)findViewById(R.id.listView);

        if(list == null)
        {
            Toast.makeText(this, "다시 시도해주세요", Toast.LENGTH_SHORT).show();
            finish();
        }
        AddressAdaptor = new AddressAdaptor(list, this,R.layout.item, 0);

        listView.setAdapter(AddressAdaptor);

        listView.getOnItemClickListener();

   }







}
