package com.kimjinhyeok.tree.dreamtreeproject;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.editor;
import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.sharedPreferences;
import static com.kimjinhyeok.tree.dreamtreeproject.R.id.htext;
import static com.kimjinhyeok.tree.dreamtreeproject.SignActivity.nav_header_id_text;
import static com.kimjinhyeok.tree.dreamtreeproject.SignActivity.nav_header_view;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Intent intent;
    LocationManager manager;
    Context context;
    boolean flag;

//    int[] imgRes = {R.drawable.icon01, R.drawable.icon02, R.drawable.icon03 };

    @Override
    protected void onStart() {
        super.onStart();
        chkGpsService();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,5,gpslistner);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1500,3,listner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = MainActivity.this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("가맹점 검색");
        //Tablayout 처리
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("주변 검색"));
        tabLayout.addTab(tabLayout.newTab().setText("지역별"));
        tabLayout.addTab(tabLayout.newTab().setText("이름별"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        manager = (LocationManager)this.getSystemService(this.LOCATION_SERVICE);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter adapter = new PagerAdapter
                (this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

//        아이콘 처리
//        tabLayout.setupWithViewPager(viewPager);
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            tabLayout.getTabAt(i).setIcon(imgRes[i]).setText("");
//        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // navigationView.setCheckedItem(R.id.nav_gallery);


        nav_header_view = navigationView.getHeaderView(0);
        nav_header_id_text = (TextView) nav_header_view.findViewById(htext);
        sharedPreferences = context.getSharedPreferences("bank",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String bank = DetailActivity.sharedPreferences.getString("bank","100,000원");
        nav_header_id_text.setText(bank);
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
                finish();
                break;
//            case  R.id.nav_gallery :
//                intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                finish();
//                break;
            case R.id.nav_slideshow :
                intent = new Intent(this, rankActivity.class);

                startActivity(intent);
           //     finish();
                break;
            case R.id.nav_manage :

               Dialog();

                break;
            case R.id.nav_sub :
                intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
          //      finish();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean chkGpsService() {

        String gps = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        Log.d(gps, "aaaa");

        if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {

            // GPS OFF 일때 Dialog 표시
            AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
            gsDialog.setTitle("위치 서비스 설정");
            gsDialog.setMessage("무선 네트워크 사용, GPS 위성 사용을 모두 체크하셔야 정확한 위치 서비스가 가능합니다.\n위치 서비스 기능을 설정하시겠습니까?");
            gsDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {


                    // GPS설정 화면으로 이동
                    Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivity(intent);
                }
            })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    }).create().show();
            return false;

        } else {
            return true;
        }
    }

    LocationListener gpslistner = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {



//            http.getInstance().lat = location.getLatitude();
//            http.getInstance().lon = location.getLongitude();
//            if(!flag)
//            {
//                http.getInstance().doAction2();
//            }
//            flag = true;
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {


        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    LocationListener listner = new LocationListener(){
        @Override
        public void onLocationChanged(Location location)
        {

//            http.getInstance().lat = location.getLatitude();
//            http.getInstance().lon= location.getLongitude();
//
//
//
//            if(!flag)
//            {
//                http.getInstance().doAction2();
//            }
//            flag = true;
        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    public void Dialog()
    {

        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(this);
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
