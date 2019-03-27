package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

import static com.kimjinhyeok.tree.dreamtreeproject.R.id.tmapView;
import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.bitmap2;


public class simpleFragment extends Fragment {
    Context context;
    View view;
    TMapMarkerItem item3;
    TMapPoint point;
    TMapView tmapview = null;

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
        View view = inflater.inflate(R.layout.simple_view, container);

        item3 = new TMapMarkerItem();
        tmapview = (TMapView) view.findViewById(tmapView);
        tmapview.setSKTMapApiKey("a648827f-32a4-4c79-a454-0dc66b507a75");
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(17);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setCompassMode(false);
        tmapview.setTrackingMode(false);

        point = viewFragment.polypoint;
        tmapview.setCenterPoint(point.getLongitude(), point.getLatitude());
        ArrayList<TMapPoint> arrays = new ArrayList<>();
        new TMapPoint(point.getLongitude(), point.getLatitude());
        bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.new3);
        Log.v("point", String.valueOf(point));
        item3.setTMapPoint(point);          //
        item3.setName("1");                //
        item3.setVisible(item3.VISIBLE);    //
        item3.setIcon(bitmap2);              //


        tmapview.addMarkerItem("1", item3);
        tmapview.sendMarkerToBack(item3);
        arrays.add(point);

//        item3.setCalloutTitle(viewFragment.name);            //
//        item3.setCalloutSubTitle(viewFragment.distance3+"km");          //
//        item3.setCanShowCallout(true);          //
//        item3.setAutoCalloutVisible(true);





        return view;
    }




}
