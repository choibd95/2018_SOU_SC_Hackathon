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
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.editor;
import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.sharedPreferences;
import static com.kimjinhyeok.tree.dreamtreeproject.R.id.htext;

public class SignActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LocationManager manager;
    Intent intent;
    Double lat;
    Double lon;
    String lat1;
    String lon1;
    boolean flag;
    View view1;
    View view2;
    View view3;
    View view4;
    View view5;
    static Context context = null;
    static View nav_header_view;
    static TextView nav_header_id_text;
    NavigationView navigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = SignActivity.this;

      // 퍼미션 권한

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }

        };


        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("지도와 검색을 하기 위해서는  \nGPS 위치접근 권한이 필요합니다")
                .setDeniedMessage("거부하시면 어플사용이 힘듭니다 ㅠㅠ \n[설정] > [권한] 에서 권한을 허용 부탁드립니다.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION
                        ,Manifest.permission.CAMERA
                        ,Manifest.permission.ACCESS_NETWORK_STATE
                        ,Manifest.permission.ACCESS_COARSE_LOCATION
                        ,Manifest.permission.ACCESS_WIFI_STATE
                        ,Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .check();


       manager = (LocationManager)this.getSystemService(this.LOCATION_SERVICE);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
      //  navigationView.setCheckedItem(R.id.nav_main);

        view1 = findViewById(R.id.bg11);
        view2 = findViewById(R.id.bg22);
        view3 = findViewById(R.id.bg33);
        view4 = findViewById(R.id.bg44);
        view5 = findViewById(R.id.bg77);



        view1.setOnClickListener(handler);
        view2.setOnClickListener(handler);
        view3.setOnClickListener(handler);
        view4.setOnClickListener(handler);
        view5.setOnClickListener(handler);


         nav_header_view = navigationView.getHeaderView(0);
         nav_header_id_text = (TextView) nav_header_view.findViewById(htext);
        sharedPreferences = context.getSharedPreferences("bank",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String bank = DetailActivity.sharedPreferences.getString("bank","100,000원");
        nav_header_id_text.setText(bank);


    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }



    @Override
    protected void onStart() {
        super.onStart();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,5,gpslistner);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1500,3,listner);
    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
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
                finish();
                break;
            case R.id.nav_manage :

                Dialog();
                break;
            case R.id.nav_sub :
                intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    LocationListener gpslistner = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {


            lat = location.getLatitude();
            lon = location.getLongitude();

            lat1 = Double.toString(lat);
            lon1 = Double.toString(lon);



            http.getInstance().lat = lat;
            http.getInstance().lon = lon;
            http.getInstance().lat1 = lat1;
            http.getInstance().lon1 = lon1;

        if(!flag)
        {
            http.getInstance().doAction2();
        }
            flag = true;
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

            lat = location.getLatitude();
            lon = location.getLongitude();

            lat1 = Double.toString(lat);
            lon1 = Double.toString(lon);

            http.getInstance().lat = lat;
            http.getInstance().lon = lon;
            http.getInstance().lat1 = lat1;
            http.getInstance().lon1 = lon1;

            if(!flag)
            {
                http.getInstance().doAction2();
            }
            flag = true;
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

    View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bg11:
                    intent = new Intent(context, InfoActivity.class);
                    startActivity(intent);
                    break;
                case R.id.bg22:
                    Dialog();
                    break;
                case R.id.bg33:
                    intent = new Intent(context, rankActivity.class);
                    startActivity(intent);

                    break;
                case R.id.bg44:
                    intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.bg77:
                    intent = new Intent(context, MapActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };


    public void Dialog()
    {

        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(this);
        alert.setTitle("잔액수정");
        alert.setMessage("잔액을 적어주세요");
        // Set an EditText view to get user input

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL                 |InputType.TYPE_NUMBER_FLAG_SIGNED);

        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(input, 0);

        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);



        alert.setView(input);

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
        // navigationView.setCheckedItem(R.id.nav_main);
    }

//
//    @Override
//    public void onBackPressed() {
//
//        if(DetailActivity.mapflag)
//        {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                finishAndRemoveTask();
//            }
//
//        }
//        else
//        {
//            super.onBackPressed();
//        }
//    }
}
