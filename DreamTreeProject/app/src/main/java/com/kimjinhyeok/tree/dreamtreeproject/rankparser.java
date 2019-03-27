package com.kimjinhyeok.tree.dreamtreeproject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-10-24.
 */

public class rankparser
{
    public static ArrayList<rankvo> gsonparse(String jsonString) {
        ArrayList<rankvo> list = new ArrayList<rankvo>();
        Gson gson = new Gson();
        list = gson.fromJson(jsonString, new TypeToken<List<rankvo>>(){}.getType());
        return list;
    }
}