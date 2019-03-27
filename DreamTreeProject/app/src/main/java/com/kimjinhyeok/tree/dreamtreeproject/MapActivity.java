package com.kimjinhyeok.tree.dreamtreeproject;

import android.*;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
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

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.editor;
import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.sharedPreferences;
import static com.kimjinhyeok.tree.dreamtreeproject.R.id.htext;
import static com.kimjinhyeok.tree.dreamtreeproject.SignActivity.nav_header_id_text;
import static com.kimjinhyeok.tree.dreamtreeproject.SignActivity.nav_header_view;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    LocationManager manager;
    Double lat;
    Double lon;
    String lat1;
    String lon1;
    boolean flag;
    static Context context = null;
    @Override
    protected void onStart() {
        super.onStart();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        chkGpsService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = MapActivity.this;

        setContentView(R.layout.map_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("가맹점 지도");

        manager = (LocationManager)this.getSystemService(this.LOCATION_SERVICE);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
               this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_camera);



        nav_header_view = navigationView.getHeaderView(0);
        nav_header_id_text = (TextView) nav_header_view.findViewById(htext);
        sharedPreferences = context.getSharedPreferences("bank",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String bank = DetailActivity.sharedPreferences.getString("bank","100,000원");
        nav_header_id_text.setText(bank);

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
                .setPermissions(android.Manifest.permission.ACCESS_FINE_LOCATION
                        , android.Manifest.permission.CAMERA
                        , android.Manifest.permission.ACCESS_NETWORK_STATE
                        , android.Manifest.permission.ACCESS_COARSE_LOCATION
                        , android.Manifest.permission.ACCESS_WIFI_STATE
                        , android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .check();


        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String cash = pref.getString("totalcash", "100,000원");
        nav_header_id_text.setText(cash);

    }

//    @Override
//    public void onBackPressed() {
//       if(DetailActivity.mapflag)
//       {
//
//       }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        Log.v("aaaaa",item.toString());
//
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//          if(viewFragment.gpsflag)
//          {
//              viewFragment.doAction1();
//              item.setIcon(R.mipmap.ic_gps_fixed_white_24dp);
//          }
//       else
//          {
//              viewFragment.doAction1();
//              item.setIcon(R.mipmap.ic_gps_not_fixed_white_24dp);
//          }
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
//                intent = new Intent(this, MapActivity.class);
//                startActivity(intent);
//                finish();
                break;

//            case  R.id.nav_gallery :
//                intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                finish();
//                break;
            case R.id.nav_slideshow :
                intent = new Intent(this, rankActivity.class);

                startActivity(intent);
            //    finish();
                break;
            case R.id.nav_manage :

            //    Dialog();

                intent = new Intent(this, BankActivity.class);
                startActivity(intent);
           //     finish();


                break;
            case R.id.nav_sub :
                intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
            //    finish();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }



    //GPS 설정 체크
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

    public void Dialog()
    {
        android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(context);
        alert.setTitle("잔액수정");
        alert.setMessage("잔액을 적어주세요");
        // Set an EditText view to get user input

        final EditText input = new EditText(context);

        input.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL                 |InputType.TYPE_NUMBER_FLAG_SIGNED);          alert.setView(input);

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
