package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.editor;
import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.sharedPreferences;
import static com.kimjinhyeok.tree.dreamtreeproject.R.id.htext;
import static com.kimjinhyeok.tree.dreamtreeproject.SignActivity.nav_header_id_text;
import static com.kimjinhyeok.tree.dreamtreeproject.SignActivity.nav_header_view;

public class rankActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Intent intent;
    static Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = rankActivity.this;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_slideshow);
        getSupportActionBar().setTitle("꿈나무추천 맛집랭킹");


        nav_header_view = navigationView.getHeaderView(0);
        nav_header_id_text = (TextView) nav_header_view.findViewById(htext);


        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String cash = pref.getString("totalcash", "100,000원");
        nav_header_id_text.setText(cash);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)


        {
//            case  R.id.nav_main :
//                intent = new Intent(this, SignActivity.class);
//                startActivity(intent);
//                finish();
//                break;
            case R.id.nav_camera :
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
         //       finish();
                break;
//            case  R.id.nav_gallery :
//                intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                finish();
//                break;
            case R.id.nav_slideshow :
                //intent = new Intent(this, rankActivity.class);

//                startActivity(intent);
//                finish();
                break;
            case R.id.nav_manage :



                intent = new Intent(this, BankActivity.class);
                startActivity(intent);
             //   finish();


            //   Dialog();

                break;
            case R.id.nav_sub :
                intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
           //     finish();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

            }
        }
    };



    public void Dialog()
    {
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(context);
        alert.setTitle("잔액수정");
        alert.setMessage("잔액을 적어주세요");
        // Set an EditText view to get user input

        final EditText input = new EditText(context);         input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL                 |InputType.TYPE_NUMBER_FLAG_SIGNED);          alert.setView(input);

        alert.setPositiveButton("수정완료", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString()+"원";
                String aa = value.toString();

                Toast.makeText(context, "수정완료", Toast.LENGTH_SHORT).show();
                // Do something with value!
                editor.putString("bank",aa);
                editor.commit();
                nav_header_id_text.setText(value.toString());
            }
        });

        alert.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

        alert.show();
    }


}
