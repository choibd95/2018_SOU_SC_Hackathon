package com.kimjinhyeok.tree.dreamtreeproject;

import android.Manifest;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.editor;
import static com.kimjinhyeok.tree.dreamtreeproject.DetailActivity.sharedPreferences;
import static com.kimjinhyeok.tree.dreamtreeproject.R.id.imageView15;
import static com.kimjinhyeok.tree.dreamtreeproject.R.id.tmapView;


public class viewFragment extends Fragment {

    LocationManager manager;
    static double lat = 37.56647;
    static double lon = 126.977963;
    static String lat1 = "37.56647";
    static String lon1 = "126.977963";
    static ArrayList<vo> list;
    static ArrayList<areavo> arealist;
    static int Addressflag;
    TMapPoint point;
    static double distance;
    static double distance2;
    static String distance3;
    ImageView imageView7;
    static ImageView imageView2;
    ImageView imageView3;
    boolean flag = true;
    static int markerflag;
    Button button;
    static  TextView downname;
    static TextView downkate;
    static TextView downkm;
    static TMapMarkerItem item1;
     static TMapMarkerItem item2;
    private static final String TAG = "MainActivity";
    static TMapView tmapview = null;
    static boolean flags;
    static TMapPoint polypoint;
    static Context context;
   static String marknums;
    static String idnumber;
    static int idnumbers;
    static Bitmap bitmap1 = null;
    static Bitmap bitmap2 = null;
    static TMapMarkerItem markeritem2;
    boolean swflag= false;
    static boolean gpsflag = true;
    static  boolean mark = false;
    static View downview;
    static String tel;
    static String address;
    static String kategorie;
    static String name;
    static String gu;
    static String dong;
    static String setkategorie;
    static MenuItem item;
    static String call;
    static int Midx;
    static String Midxs;
    static boolean mainflag;
    View nvveiw;
    View callview;
    Intent intent;
    static boolean polysw = false;
    static String tels;
    static boolean loflag = true;
    ArrayList<TMapPoint> arrays;
    static boolean mapplag = false;
    static String mapname;
    boolean map2plag = false;
    static ImageView nvimgview;

    ImageView helpbt;
    ImageView gpsbt;

    CustomDialog helpdialog;


    boolean startflag = true;


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
            switch (view.getId()) {
                case R.id.imageView:

                    http.getInstance().urlp = 1;
                    doAction1();

                    break;

//                case R.id.imageView2:
//
//                    switch (markerflag) {
//                        case 0:
//                            Addressflag = 0;
//
//                            http.getInstance().lat = lat;
//                            http.getInstance().lon = lon;
//
//                            http.getInstance().lat1 = Double.toString(lat);
//                            http.getInstance().lon1 = Double.toString(lon);
//
//                            http.getInstance().urlp = 1;
//                            http.getInstance().doAction1();
//                            imageView2.setImageResource(R.drawable.navigation2);
//                            break;
//                        case 1:
//                            removeAllMarker();
//                            break;
//                    }
//                    break;

                case R.id.imageView7:
                    DialogRadio();
                    break;
                case R.id.nv1:
                    call();
                    break;

                case R.id.nv2:
                    point = item1.getTMapPoint();
                      drawMapPath(polypoint);
                    break;

                case imageView15:
                    switch (markerflag) {
                        case 0:
                            jsoncall();
                            break;
                        case 1:
                            removeAllMarker();
                            break;

                          }
                    break;

//                case R.id.button7:
//                      point = item1.getTMapPoint();
//                      drawMapPath(polypoint);

//                    break;

                case R.id.downview:

                    detailgo();


                    break;


                case R.id.gps :

                    tmapview.setCenterPoint(lon, lat,true);
                    Toast.makeText(context, "현재위치를 표시합니다", Toast.LENGTH_LONG).show();

                    break;

                case R.id.help :
                    Dialog();

                    break;




            }
        }
    };


//    void listgo()
//    {
//        Intent intent = new Intent(context, Address.class);
//        startActivityForResult(intent, 3000);
//
//    }

   public static void doAction1() {


//        imageView.setBackgroundResource(R.color.colorPrimary);
        if (http.getInstance().lon == null)

        {

            Toast.makeText(context, "현재위치를 찾고있습니다 5~10초간 소요됩니다.", Toast.LENGTH_SHORT).show();
        }
        else {
            if(gpsflag)
            {
                tmapview.setLocationPoint(http.getInstance().lon, http.getInstance().lat);
                tmapview.setCenterPoint(http.getInstance().lon, http.getInstance().lat);
//                Toast.makeText(context, "현재위치를 실시간으로 표시합니다.", Toast.LENGTH_SHORT).show();
                gpsflag = false;
            }
            else
            {
                gpsflag = true;
                tmapview.setTrackingMode(false);
//                Toast.makeText(context, "현재위치를 찾지 않습니다.", Toast.LENGTH_SHORT).show();
            }

        }


//        tmapview.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
//            @Override
//            public void onCalloutRightButton(TMapMarkerItem markerItem) {
//                String strMessage = "현재위치부터 도착지점까지 거리는" + markerItem.getID() + "KM 입니다";
///               strMessage = "ID: " + markerItem.getID() + " " + "Title " + markerItem.getCalloutTitle();
//                Toast.makeText(getActivity(), strMessage, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), strMessage, Toast.LENGTH_SHORT).show();
//
//                point = markerItem.getTMapPoint();
//                drawMapPath(point);
//
//
//
//            }
//
//        });



    }


    public static void showMarkerPoint() {
        /////////////////////////////////////////////////////////////////////////////

        list = http.getInstance().list;


        if(http.getInstance().list == null || http.getInstance().lon == null)

        {

            Toast.makeText(context, "현재위치를 찾고있습니다 잠시만 기다려주세요", Toast.LENGTH_SHORT).show();
            markerflag = 0;
        }
        else
        {


            markerflag = 1;
            int size = list.size();
            Toast.makeText(viewFragment.context, "주변 꿈나무카드 가맹점을 조회합니다.\n지도의 마커를 눌러주세요", Toast.LENGTH_LONG).show();
       if(http.getInstance().lon != null)
       {
           tmapview.setLocationPoint(lon,lat);
       }

        if(item != null)
        {
         //   item.setIcon(R.mipmap.ic_gps_fixed_white_24dp);
        }

            gpsflag = false;




        Log.v(TAG, "5");
        double pointlat = 0;
        double pointlon = 0;
        double pointlat2;
        double ponitlon2;
        String name;
        String TEL;
        String strID;
        int mMarkerID = 0;
        TMapPoint point; //

        ArrayList<TMapPoint> arrays = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            marknums = Integer.toString(i);

            pointlat = list.get(i).getLatitude();
            pointlon = list.get(i).getLongitude();
            name = list.get(i).getName();
            TEL = list.get(i).getTEL();

            distance = list.get(i).getDistance();
            distance2 = Double.parseDouble(String.format("%.1f", distance));
            distance3 = Double.toString(distance2);

            point = new TMapPoint(pointlat, pointlon);  //
            item1 = new TMapMarkerItem();               //

            item1.setTMapPoint(point);          //
            item1.setName(name);                //
            item1.setVisible(item1.VISIBLE);    //
            item1.setIcon(bitmap1);              //2
//            item1.setCalloutTitle(name);            //
//            item1.setCalloutSubTitle(TEL);          //
//            item1.setCanShowCallout(true);          //
//            item1.setAutoCalloutVisible(false);          //
//            item1.setCalloutRightButtonImage(bitmap_i);
//            strID = String.format(distance3);
            tmapview.addMarkerItem(marknums, item1);
            tmapview.sendMarkerToBack(item1);
            arrays.add(point);
        }
//            TMapInfo info = tmapview.getDisplayTMapInfo(arrays);
//            int level = info.getTMapZoomLevel();
            tmapview.setZoomLevel(14);
//         TMapInfo info = tmapview.getDisplayTMapInfo(arrays);
//       pointlat2 = info.getTMapPoint().getLatitude();
//         ponitlon2 = info.getTMapPoint().getLongitude();
//
//         tmapview.setTrackingMode(false);
//         int level = info.getTMapZoomLevel();
//         tmapview.setZoomLevel(level);
//         tmapview.setCenterPoint(ponitlon2,pointlat2);

        marker();
            if(item2 != null)
            {
                item2.setVisible(item2.GONE);
            }

        }
    }


    public static void removeAllMarker() {

//        imageView2.setImageResource(R.drawable.navigation);
        nvimgview.setImageResource(R.drawable.non_select);
        tmapview.removeAllMarkerItem();
        tmapview.removeTMapPath();
        markerflag = 0;
        downview.setVisibility(View.GONE);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_view, container);
//        imageView = (ImageView) view.findViewById(R.id.imageView);
//        imageView.setOnClickListener(handler);

        downview = view.findViewById(R.id.downview);
        downview.setOnClickListener(handler);


//        imageView2 = (ImageView) view.findViewById(R.id.imageView2);
//        imageView2.setOnClickListener(handler);

        imageView7 = (ImageView) view.findViewById(R.id.imageView7);
        imageView7.setOnClickListener(handler);

        nvveiw =  view.findViewById(R.id.nv2);
        nvveiw.setOnClickListener(handler);

        callview =  view.findViewById(R.id.nv1);
        callview.setOnClickListener(handler);

        nvimgview = (ImageView) view.findViewById(imageView15);
        nvimgview.setOnClickListener(handler);

//        namebutton = (Button) view.findViewById(R.id.button7);
//        namebutton.setOnClickListener(handler);

//        imageView3 = (ImageView) view.findViewById(R.id.imageView3);
//        imageView3.setOnClickListener(handler);


         downname =  (TextView) view.findViewById(R.id.textView_name);
         downkate =  (TextView) view.findViewById(R.id.textView_kategorie);
         downkm =  (TextView) view.findViewById(R.id.textView_km);



        tmapview = (TMapView) view.findViewById(tmapView);
        tmapview.setSKTMapApiKey("a648827f-32a4-4c79-a454-0dc66b507a75");
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(14);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(false);
        tmapview.setTrackingMode(false);

        bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.new4);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.new3);
        setHasOptionsMenu(true);

        if(http.getInstance().lon == null)
        {
            tmapview.setCenterPoint(126.985022, 37.566474,true);
            tmapview.setLocationPoint(126.985022, 37.566474);
        }else
        {
            tmapview.setCenterPoint(http.getInstance().lon, http.getInstance().lat,true);
            tmapview.setLocationPoint(http.getInstance().lon, http.getInstance().lat);
        }


        gpsbt =  (ImageView) view.findViewById(R.id.gps);
        gpsbt.setOnClickListener(handler);

        helpbt =  (ImageView) view.findViewById(R.id.help);
        helpbt.setOnClickListener(handler);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
//        {
//            gpsbt.setBackground(new ShapeDrawable(new OvalShape()));
//            helpbt.setBackground(new ShapeDrawable(new OvalShape()));
//
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                gpsbt.setClipToOutline(true);
////                helpbt.setClipToOutline(true);
////            }
//
//
//
//        }


        list = http.getInstance().list;



       // startdialog();

        Dialog();


        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        tmapview.invalidate();

      if(mainflag)
      {

          arrays = new ArrayList<>();
          tmapview.setCenterPoint(polypoint.getLongitude(), polypoint.getLatitude(),true);
          new TMapPoint(polypoint.getLongitude(), polypoint.getLatitude());
          item2 = new TMapMarkerItem();
          item2.setVisible(item2.VISIBLE);    //
          item2.setIcon(bitmap2);
          item2.setTMapPoint(polypoint);          //
          tmapview.addMarkerItem("777", item2);
          arrays.add(polypoint);

          if (polysw)
          {
              drawMapPath(polypoint);
              polysw = false;
          }
//          else
//          {
//              arrays = new ArrayList<>();
//              tmapview.setCenterPoint(polypoint.getLongitude(), polypoint.getLatitude());
//              new TMapPoint(polypoint.getLongitude(), polypoint.getLatitude());
//              item1 = new TMapMarkerItem();
//              item1.setVisible(item1.VISIBLE);    //
//              item1.setIcon(bitmap2);
//              item1.setTMapPoint(polypoint);          //
//              tmapview.addMarkerItem("777", item1);
//              arrays.add(point);
//          }
          mainflag = false;
      }






        if(startflag)
        {

            tmapview.setCenterPoint(lon, lat);

            jsoncall();

            startflag = false;
        }





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



                tmapview.setLocationPoint(lon, lat);







            if (flag && loflag) {

                flag = false;
          //      item.setIcon(R.mipmap.ic_gps_fixed_white_24dp);
                gpsflag = false;

            }

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
    LocationListener listner = new LocationListener() {
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

            tmapview.setLocationPoint(lon, lat);



            if (flag && loflag) {
                flag = false;

              if(item != null)
              {
      //            item.setIcon(R.mipmap.ic_gps_fixed_white_24dp);
              }
                gpsflag = false;
            }






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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        manager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);


    }

    @Override
    public void onStart() {
        super.onStart();

          if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

              return;
          }




        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, gpslistner);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1500, 3, listner);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        manager.removeUpdates(listner);
    }


    /**
     * drawMapPath
     * 지도에 시작-종료 점에 대해서 경로를 표시한다.
     *
     * @param point
     */


    public void drawMapPath(TMapPoint point) {
        TMapPoint point1 = tmapview.getLocationPoint();
        TMapPoint point2 = point;

        TMapData tmapdata = new TMapData();

        tmapdata.findPathData(point1, point2, new TMapData.FindPathDataListenerCallback() {

            @Override
            public void onFindPathData(TMapPolyLine polyLine) {
                tmapview.addTMapPath(polyLine);
            }
        });
    }


    /**
     * removeMapPath
     * 경로 표시를 삭제한다.
     */
    public void removeMapPath() {
        tmapview.removeTMapPath();
    }



    private void DialogRadio() {
        final CharSequence[] address = {"전체", "한식", "중식", "분식", "제과"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(getContext());
        alt_bld.setIcon(R.drawable.menu3);
        alt_bld.setTitle("카테고리");
        alt_bld.setSingleChoiceItems(address, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

              

              if( address[item] == "전체" )
              {
                  removeAllMarker();
                  http.getInstance().urlp = 1;
                gogomy();
                  dialog.cancel();
              }
              else
              {
                  removeAllMarker();
                  setkategorie = (String) address[item];
                  http.getInstance().urlp = 2;
                  gogomy();
                  markerflag = 0;
                  dialog.cancel();
              }
            }
        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }


public static void marker()
{
    tmapview.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {

        @Override
        public boolean onPressUpEvent(ArrayList<TMapMarkerItem>
                                              markerlist,ArrayList<TMapPOIItem> poilist, TMapPoint point, PointF pointf) {
            if(markerlist.size()  == 1 ) {
                if (mark) {
                    markeritem2.setIcon(bitmap1);
                }


                tmapview.setCenterPoint(point.getLongitude(), point.getLatitude(), true);


                polypoint = markerlist.get(0).getTMapPoint();
                idnumber = markerlist.get(0).getID();
                idnumbers = Integer.parseInt(idnumber);
                if (list.get(idnumbers) != null) {

                    downname.setText(list.get(idnumbers).getName());
                    downkate.setText(list.get(idnumbers).getKategorie());
                    distance = list.get(idnumbers).getDistance();
                    distance2 = Double.parseDouble(String.format("%.1f", distance));
                    distance3 = Double.toString(distance2);
                    downkm.setText(distance3 + "km");
                    Midx = list.get(idnumbers).getMidx();
                    Midxs = String.valueOf(Midx);

                    flags = false;
                    mark = true;
                    markerlist.get(0).setIcon(bitmap2);
                    markeritem2 = markerlist.get(0);
                    downview.setVisibility(View.VISIBLE);


                    tel = list.get(idnumbers).TEL;
                    tels = tel.substring(0, 1);

                    if (tels.equals("0")) {

                    } else {
                        tel = "02-" + list.get(idnumbers).TEL;
                    }

                    address = list.get(idnumbers).Address;
                    kategorie = list.get(idnumbers).Kategorie;
                    name = list.get(idnumbers).Name;
                    gu = list.get(idnumbers).Gu;
                    dong = list.get(idnumbers).Dong;
                } else {
               //     item.setIcon(R.mipmap.ic_gps_not_fixed_white_24dp);
                    //      downview.setVisibility(View.GONE);
                    flags = false;
                    gpsflag = true;
                }
            }
            return false;
        }
        @Override
        public boolean onPressEvent(ArrayList<TMapMarkerItem>
                                            markerlist,ArrayList<TMapPOIItem> poilist, TMapPoint point, PointF pointf) {

            return false;
        }
    });

}

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map, menu);
        super.onCreateOptionsMenu(menu, inflater);
//       item = menu.getItem(2);

        MenuItem toolItemSearchIcon = menu.findItem(R.id.tool_item_search);
        toolItemSearchIcon.setIcon(getResources().getDrawable(R.mipmap.ic_search_white_48dp));

        initSearchView(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//          if(!swflag)
//          {
//              item.setIcon(R.mipmap.ic_gps_fixed_white_24dp);
//              tmapview.setCenterPoint(lon, lat);
//            swflag = true;
//          }
//       else
//          {
//              item.setIcon(R.mipmap.ic_gps_not_fixed_white_24dp);
//                swflag = false;
//          }
//            return true;
//        }
        if(id == R.id.tool_item_star_mark)
        {
            DialogRadio2();
        }

        return super.onOptionsItemSelected(item);
    }

    public void call()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel));
        startActivity(intent);
    }

    /** 서치뷰 만들기*/
    private void initSearchView(Menu menu) {

        /**  SearchView Hint 변경하기    */
        MenuItem searchItem = menu.findItem(R.id.tool_item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("가맹점 이름을 검색하세요");

        /**     SearchView 입력 글자색과 힌트 색상 변경하기    */
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.rgb(255, 255, 255));
        searchAutoComplete.setTextColor(Color.rgb(255, 255, 255));
        searchAutoComplete.setTextSize(18);

        /**     SearchView 확장/축소 이벤트 처리    */
        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        };
        MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);


        /** 검색 텍스트를 입력 및 변경할 때 진행되는 것과 정확하게 제어할 수 있는 콜백*/
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    /** 검색어 입력시*/
                    @Override
                    public boolean onQueryTextChange(String newText) {

                        return false;
                    }

                    /** 검색어 완료시*/
                    @Override
                    public boolean onQueryTextSubmit(String query)
                    {

                        mapplag = true;
                        map2plag =true;
                        if(query.equals(""))
                        {
                            Toast.makeText(context, "검색어를 적어주세요", Toast.LENGTH_SHORT).show();
                        }else
                        {

                            http.getInstance().sname = query;
                            http.getInstance().urlp = 7;
                            http.getInstance().doAction2();

                        }
                        return false;
                    }
                }
        );

        /** SearchManager를 사용하는 검색가능한 설정에 서치뷰를 연결시키면 여러분의 액티비티에 추가된 메타데이터요소를 분석하는
         시스템 서비스가 서치뷰로 통과할 수 있는 검색가능한 정보를 생성한다 */
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        // Can be replaced with getComponentName()
        // if this searchable activity is the current activity
        ComponentName componentName = new ComponentName(context, String.valueOf(MapActivity.context));
    }




    /** Getting the search query = 검색가능한 액티티비 작동
     실제 검색 텍스트를 가져올 액션을 검색하고 인텐트로에서 추가적인 검색을 가져올 수 있*/
    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(
                intent.getAction())){
            String query = intent.getStringExtra(
                    SearchManager.QUERY);
            doMySearch(query);
        }
    }

    private void doMySearch(String query)
    {
    }


    public static void showMarkerPoint2() {
        /////////////////////////////////////////////////////////////////////////////

        removeAllMarker();

        if(http.getInstance().arealist == null || http.getInstance().arealist.size() == 0)
        {

            Toast.makeText(context, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
            markerflag = 0;
        }
        else
        {

            markerflag = 1;
            int size = arealist.size();



            if(item != null)
            {
         //       item.setIcon(R.mipmap.ic_gps_fixed_white_24dp);
            }

            gpsflag = false;




            Log.v(TAG, "5");
            double pointlat = 0;
            double pointlon = 0;
            double pointlat2;
            double ponitlon2;
            String name;
            String TEL;
            String strID;
            int mMarkerID = 0;
            TMapPoint point; //

            ArrayList<TMapPoint> arrays = new ArrayList<>();








            for (int i = 0; i < size; i++) {


                if(arealist.get(i).getLatitude()>0) {

                    marknums = Integer.toString(i);

                    pointlat = arealist.get(i).getLatitude();
                    pointlon = arealist.get(i).getLongitude();
                    name = arealist.get(i).getName();
                    TEL = arealist.get(i).getTEL();

                    distance3 = "거리 알수없음";

                    point = new TMapPoint(pointlat, pointlon);  //
                    item1 = new TMapMarkerItem();               //

                    item1.setTMapPoint(point);          //
                    item1.setName(name);                //
                    item1.setVisible(item1.VISIBLE);    //
                    item1.setIcon(bitmap1);              //
//            item1.setCalloutTitle(name);            //
//            item1.setCalloutSubTitle(TEL);          //
//            item1.setCanShowCallout(true);          //
//            item1.setAutoCalloutVisible(false);          //
//            item1.setCalloutRightButtonImage(bitmap_i);
//            strID = String.format(distance3);
                    tmapview.addMarkerItem(marknums, item1);
                    tmapview.sendMarkerToBack(item1);
                    arrays.add(point);
                }
            }


        arrays.get(0).getLongitude();

                  arrays.get(0).getLatitude();

            tmapview.setCenterPoint( arrays.get(3).getLongitude(),arrays.get(3).getLatitude(),true);
            tmapview.setZoomLevel(14);
//         TMapInfo info = tmapview.getDisplayTMapInfo(arrays);
//       pointlat2 = info.getTMapPoint().getLatitude();
//         ponitlon2 = info.getTMapPoint().getLongitude();
//
//         tmapview.setTrackingMode(false);
//         int level = info.getTMapZoomLevel();
//         tmapview.setZoomLevel(level);
//         tmapview.setCenterPoint(ponitlon2,pointlat2);

            marker2();
            if(item2 != null)
            {
                item2.setVisible(item2.GONE);
            }

        }
    }
    public static void marker2()
    {
        tmapview.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {


            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem>
                                                  markerarealist, ArrayList<TMapPOIItem> poiarealist, TMapPoint point, PointF pointf) {
                if (markerarealist.size() == 1) {
                    if (mark) {
                        markeritem2.setIcon(bitmap1);
                    }
                    polypoint = markerarealist.get(0).getTMapPoint();
                    idnumber = markerarealist.get(0).getID();
                    idnumbers = Integer.parseInt(idnumber);
                    downname.setText(arealist.get(idnumbers).getName());
                    downkate.setText(arealist.get(idnumbers).getKategorie());
                    downkm.setText("거리 알수없음");
                    Midx = arealist.get(idnumbers).getMidx();
                    Midxs = String.valueOf(Midx);

                    flags = false;
                    mark = true;
                    markerarealist.get(0).setIcon(bitmap2);
                    markeritem2 = markerarealist.get(0);
                    downview.setVisibility(View.VISIBLE);

                    tmapview.setCenterPoint(point.getLongitude(),point.getLatitude(),true);


                    tel = arealist.get(idnumbers).TEL;
                    tels = tel.substring(0, 1);

                    if (tels.equals("0")) {

                    } else {
                        tel = "02-" + arealist.get(idnumbers).TEL;
                    }

                    address = arealist.get(idnumbers).Address;
                    kategorie = arealist.get(idnumbers).Kategorie;
                    name = arealist.get(idnumbers).Name;
                    gu = arealist.get(idnumbers).Gu;
                    dong = arealist.get(idnumbers).Dong;
                } else {
//                    item.setIcon(R.mipmap.ic_gps_not_fixed_white_24dp);
                    downview.setVisibility(View.GONE);
                    flags = false;
                    gpsflag = true;
                }
                return false;
            }


        });



}

    public void DialogRadio2()
    {

        final CharSequence[] address = {"강남구", "강동구", "강북구" , "강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구",
                "동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구", "중구","중랑구"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(getContext());
        alt_bld.setIcon(R.drawable.main_white);
        alt_bld.setTitle("지역 선택");

        alt_bld.setSingleChoiceItems(address, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                http.getInstance().areaname = address[item].toString();

                mapplag = true;
                http.getInstance().urlp = 3;
                http.getInstance().doAction2();
                dialog.cancel();

            }

        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    public void gogomy()
    {
        TMapPoint tpoint = tmapview.getCenterPoint();


        double lont2  = tpoint.getLongitude();
        double lat2 = tpoint.getLatitude();



        http.getInstance().lat = lat2;
        http.getInstance().lon = lont2;
        http.getInstance().lat1 = Double.toString(lat2);
        http.getInstance().lon1 = Double.toString(lont2);

        http.getInstance().doAction1();


    }

    public void detailgo()
    {
        intent = new Intent(getContext(), DetailActivity.class);
        startActivity(intent);

//        getActivity().finish();


    }




    public void jsoncall()
    {


        http.getInstance().urlp = 1;
        Addressflag = 0;
        nvimgview.setImageResource(R.drawable.main_white);
        gogomy();


    }


    public void Dialog(){
        helpdialog = new CustomDialog(context,
                "", // 제목
                "", // 내용
                leftListener); // 왼쪽 버튼 이벤트
        // 오른쪽 버튼 이벤트

        //요청 이 다이어로그를 종료할 수 있게 지정함
        helpdialog.setCancelable(true);
        helpdialog.getWindow().setGravity(Gravity.CENTER);
        helpdialog.show();
    }

    //다이얼로그 클릭이벤트
    private View.OnClickListener leftListener = new View.OnClickListener() {
        public void onClick(View v) {
            helpdialog.dismiss();
        }
    };




    void startdialog()
    {

        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        int flag = pref.getInt("one", 0);

        if(flag == 0)
        {
            Dialog();
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("one", 1);
        editor.commit();

    }


}




