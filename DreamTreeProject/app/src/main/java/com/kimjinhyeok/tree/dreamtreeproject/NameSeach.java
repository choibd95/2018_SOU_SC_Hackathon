package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapPoint;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.Midx;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.Midxs;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.mainflag;

public class NameSeach extends Fragment
{
    static Context context;
    View view;
    static AreaAdaptor AreaAdaptor;
    static ListView listView;
    static String area;
    private http    testHttp    =null;
    View bg1;
    TextView text;
    static EditText editText;
    ImageView imageView;
    static String name;
    static boolean flag =false;
    InputMethodManager imm;

    public static void click()
    {

        if(http.getInstance().arealist != null)
        {
            flag = true;
            AreaAdaptor = new AreaAdaptor(http.getInstance().arealist, context, R.layout.areaitem, 0);
            listView.setAdapter(AreaAdaptor);
            AreaAdaptor.notifyDataSetChanged();
            listView.getOnItemClickListener();
            if(http.getInstance().arealist.size() != 0)
            {
                Toast.makeText(context, name+" 검색 완료", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "해당 이름의 가맹점은 없습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context, "다시 시도해주세요.", Toast.LENGTH_SHORT).show();
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

         imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        view = inflater.inflate(R.layout.namesearch, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        listView.setOnItemClickListener(itemhandler);
        text = (TextView) view.findViewById(R.id.addtext);


        bg1 = view.findViewById(R.id.bg11);
        bg1.setOnClickListener(handler);

        editText = (EditText) view.findViewById(R.id.editText);
        if(NameSeach.editText != null)
        {
            NameSeach.editText.setFocusable(true);
        }

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                Log.v("찍혀라","찍히냐?");
                if (actionId==EditorInfo.IME_ACTION_DONE)
                {
                    imageView.performClick();
                    Log.v("찍혀라","제발");
                    return false;
                }
            return false;
            }
        }

        );

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
              public void onFocusChange(View v, boolean hasFocus)
              {
                     if(hasFocus == false)
                     {
                         imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
                         }else
                     {
                         imm.showSoftInput(editText, 0);
                     }
                   }
             });

        imageView = (ImageView) view.findViewById(R.id.symbol);
        imageView.setOnClickListener(handler);



        return view;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);

    }

    @Override
    public void onResume() {
        super.onResume();

        imm.showSoftInput(editText, 0);

    }

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.symbol :
                    start();
                    break;
            }
        }
    };

        public void start()
        {
            name = editText.getText().toString();
            if(name.equals(""))
            {
                Toast.makeText(context, "검색어를 적어주세요", Toast.LENGTH_SHORT).show();
            }else
            {
                http.getInstance().urlp = 7;
                http.getInstance().doAction2();
            }

        }

    ListView.OnItemClickListener itemhandler = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(http.getInstance().arealist != null)
            {

            TMapPoint point = new TMapPoint(http.getInstance().arealist.get(position).getLatitude(), http.getInstance().arealist.get(position).getLongitude());

                viewFragment.name = http.getInstance().arealist.get(position).getName();
            viewFragment.tel = http.getInstance().arealist.get(position).getTEL();
            viewFragment.gu = http.getInstance().arealist.get(position).getGu();
            viewFragment.dong = http.getInstance().arealist.get(position).getDong();
            viewFragment.address = http.getInstance().arealist.get(position).getAddress();
            viewFragment.kategorie = http.getInstance().arealist.get(position).getKategorie();
                viewFragment.Midx = http.getInstance().arealist.get(position).getMidx();
                Midxs = String.valueOf(Midx);
            viewFragment.distance3 =  "거리 알수없음";
            viewFragment.polypoint = point;
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

}