package com.kimjinhyeok.tree.dreamtreeproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class loadActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Intent intent;
    static Context context = null;


    static reviewAdaptor reviewAdaptor;
    static ListView listView;
    ProgressDialog dialog = null;
    static TextView noview;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        http.getInstance().urlp = 11;
        http.getInstance().doAction2();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = loadActivity.this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       // navigationView.setCheckedItem(R.id.nav_main);

        getSupportActionBar().setTitle("리뷰 보기");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_36dp);

        listView = (ListView) findViewById(R.id.viewlist);


        reviewAdaptor = new reviewAdaptor(http.getInstance().reviewlist, context,R.layout.reviewitem, 0);

        listView.setAdapter(reviewAdaptor);
        reviewAdaptor.notifyDataSetChanged();

       noview = (TextView) findViewById(R.id.noview);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

            }
        }
    };



    public static void click()
    {

        if(http.getInstance().reviewlist != null)
        {
            reviewAdaptor = new reviewAdaptor(http.getInstance().reviewlist, context,R.layout.reviewitem, 0);
            listView.setAdapter(reviewAdaptor);
            reviewAdaptor.notifyDataSetChanged();
            if(http.getInstance().reviewlist.size() != 0)
            {
                noview.setVisibility(View.GONE);
            }
        }
        else
        {
            Toast.makeText(context, "인터넷을 확인해주세요", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}
