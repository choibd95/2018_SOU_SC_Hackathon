package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.loflag;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.mainflag;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.polysw;

public class DetailActivity extends AppCompatActivity {

    Intent intent;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    static TextView textView4;
    TextView textView5;
    View likeview;
    View callview;
    View nvview;
    View joinview;
    View loadview;
    ImageView likeImage;
    TextView liketext;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    String Token = FirebaseInstanceId.getInstance().getToken();
    Context context;
    View view;
    String tel;
    boolean likeflag = false;
    TextView text;
   static int like;
    static boolean mapflag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        http.urlp = 9;
        http.getInstance().doAction2();

        context = DetailActivity.this;
        setContentView(R.layout.detail_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle(viewFragment.name);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);

        textView1 = (TextView) findViewById(R.id.textView5);
        textView2 = (TextView) findViewById(R.id.textView6);
        textView3 = (TextView) findViewById(R.id.textView7);
        textView4 = (TextView) findViewById(R.id.textView8);
        textView5 = (TextView) findViewById(R.id.textView9);
        liketext = (TextView) findViewById(R.id.liketext);

        like = 0;
        textView4.setText(like+"개");

        likeview = findViewById(R.id.likeview);
        likeview.setOnClickListener(handler);

        callview = findViewById(R.id.nv1);
        callview.setOnClickListener(handler);

        nvview = findViewById(R.id.nv2);
        nvview.setOnClickListener(handler);

        text = (TextView) findViewById(R.id.text1);

        likeImage = (ImageView) findViewById(R.id.likebutton);

        joinview = findViewById(R.id.bg11);
        joinview.setOnClickListener(handler);

        loadview = findViewById(R.id.bg3);
        loadview.setOnClickListener(handler);


        tel = viewFragment.tel;

        String tels = tel.substring(0,1);
        Log.v("tels",tels);
        if(tels.equals("0"))
        {

        }else
        {
            tel = "02-"+tel;
        }

        textView1.setText(tel);



        textView2.setText(viewFragment.gu+" "+viewFragment.dong+" "+viewFragment.address);
        textView3.setText(viewFragment.kategorie);


        if(viewFragment.distance3 != null)
        {

        if(viewFragment.distance3.equals("거리 알수없음"))
        {
            textView5.setText(viewFragment.distance3);
        }else
        {
            textView5.setText(viewFragment.distance3+" km");
        }
        }else
        {
            textView5.setText("거리 알수없음");
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        tokensave();
    }

    @Override
    public void onBackPressed() {

        if(mapflag)
        {
//            intent = new Intent(context, SignActivity.class);
//            startActivity(intent);
            finish();
        }else
        {
            super.onBackPressed();
        }


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    public void tokensave()
    {
        sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getInt("token",0) == 0)
        {

            http.getInstance().urlp = 4;
            http.getInstance().doAction2();
            Log.v("요청한다","요청한다.");
        }else
        {
            Log.v("성공성공","성공했다.");
        }
    }

    public static void one()
    {
        editor.putInt("token",1);
        editor.commit();
        int a = sharedPreferences.getInt("token",1);
        Log.v("token", String.valueOf(a));
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        item.setIcon(R.drawable.icon01);
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//
//            http.getInstance().doAction2();
//            Toast.makeText(this, "aa"+http.getInstance().lat1, Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }




    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.likeview:
                    like();
                    break;

                case R.id.nv1:
                    call();

                    break;

                case R.id.nv2:
                    polysw = true;
                    mainflag = true;
                    loflag = false;
                    intent = new Intent(context, MapActivity.class);
                    startActivity(intent);
                    mapflag = true;
                    break;

                case R.id.bg11:

                    intent = new Intent(context, RippleActivity.class);
                    startActivity(intent);
                    break;
                case  R.id.bg3 :


                    intent = new Intent(context, loadActivity.class);
                    startActivity(intent);
                    break;


            }
        }
    };


    public void like()
    {


        if(!likeflag)
        {
            http.urlp = 5;
            http.getInstance().doAction2();
            likeflag = true;
            likeImage.setImageResource(R.drawable.like);
            textView4.setText(like+1+"개");
            textView4.setTextColor(getResources().getColor(R.color.maincolor));
            liketext.setTextColor(getResources().getColor(R.color.maincolor));
        }else
        {
            http.urlp = 6;
            http.getInstance().doAction2();
            likeflag = false;
            likeImage.setImageResource(R.drawable.like750);
            textView4.setText(like+"개");
            textView4.setTextColor(getResources().getColor(R.color.black));
            liketext.setTextColor(getResources().getColor(R.color.black));
        }

    }

    public void call()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel));
        startActivity(intent);
    }

    public static void good()
    {

        if(http.getInstance().likelist != null && http.getInstance().likelist.size() != 0)
        {
            like = http.getInstance().likelist.get(0).getLike_cnt();
            textView4.setText(like+"개");
        }

    }




}
