package com.kimjinhyeok.tree.dreamtreeproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-09-21.
 */
public class parser
{

    public static ArrayList<vo> parse(String jsonString) {
        ArrayList<vo> list = new ArrayList<vo>();
        vo listVO = new vo();

        JSONArray jsonArray = null;
        JSONObject tempObject = null;
        vo vo = null;
        try {
            jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                tempObject = jsonArray.getJSONObject(i);
                vo = new vo();

                vo.setNumber(tempObject.getInt("Number"));
                vo.setGu(tempObject.getString("Gu"));
                vo.setDong(tempObject.getString("Dong"));
                vo.setName(tempObject.getString("Name"));
                vo.setAddress(tempObject.getString("Address"));
                vo.setLatitude(tempObject.getDouble("Latitude"));
                vo.setLongitude(tempObject.getDouble("Longitude"));
                vo.setTEL(tempObject.getString("TEL"));
                vo.setKategorie(tempObject.getString("Kategorie"));
                vo.setMidx(tempObject.getInt("Midx"));
                vo.setDatatime(tempObject.getString("Datatime"));
                vo.setDistance(tempObject.getDouble("distance"));
                list.add(vo);
            }

        } catch (JSONException e) {

        }
        return list;
    }
}
